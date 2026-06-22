#!/usr/bin/env python3
# #%L
# kftc-financial-institution-info
# %%
# Copyright (C) 2024 - 2025 Jinahya, Inc.
# %%
# Licensed under the Apache License, Version 2.0 (the "License").
# #L%
"""
Convert a KFTC pipe-delimited EUC-KR text file into an .xlsx workbook.

This replaces the manual Excel step in _WORKFLOW.md:
    "codefilex.text 를 엑셀에서 Get Data / Text/CSV 로, EUC-KR 인코딩으로 적재한 후 .xlsx 로 저장한다."

Every line is expected to hold exactly 9 pipe ('|') delimited fields. The first
output row is a header (skipped by the consuming test); each subsequent row holds
the 9 fields as inline-string cells (no type coercion, so leading zeros survive).

Stdlib only -- no third-party dependency. Usage:
    python3 _textToXlsx.py <input.text> <output.xlsx> [--encoding EUC-KR] [--fields 9]
"""
import argparse
import sys
import zipfile

COLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def xml_escape(s):
    out = []
    for ch in s:
        if ch == "&":
            out.append("&amp;")
        elif ch == "<":
            out.append("&lt;")
        elif ch == ">":
            out.append("&gt;")
        elif ch == '"':
            out.append("&quot;")
        elif ord(ch) < 0x20 and ch not in "\t\n\r":
            # control chars are illegal in XML 1.0; drop them
            continue
        else:
            out.append(ch)
    return "".join(out)


def cell(ref, value):
    return (
        '<c r="%s" t="inlineStr"><is><t xml:space="preserve">%s</t></is></c>'
        % (ref, xml_escape(value))
    )


def build_sheet(rows, fields):
    n_rows = len(rows) + 1  # + header
    last_col = COLS[fields - 1]
    parts = [
        '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>',
        '<worksheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main">',
        '<dimension ref="A1:%s%d"/>' % (last_col, n_rows),
        "<sheetData>",
    ]
    # header row (content irrelevant; the consuming test skips row 0)
    header = "".join(
        cell("%s1" % COLS[c], str(c)) for c in range(fields)
    )
    parts.append('<row r="1">%s</row>' % header)
    for i, tokens in enumerate(rows):
        r = i + 2
        body = "".join(
            cell("%s%d" % (COLS[c], r), tokens[c]) for c in range(fields)
        )
        parts.append('<row r="%d">%s</row>' % (r, body))
    parts.append("</sheetData></worksheet>")
    return "".join(parts)


CONTENT_TYPES = (
    '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
    '<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">'
    '<Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/>'
    '<Default Extension="xml" ContentType="application/xml"/>'
    '<Override PartName="/xl/workbook.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml"/>'
    '<Override PartName="/xl/worksheets/sheet1.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml"/>'
    '<Override PartName="/xl/styles.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml"/>'
    "</Types>"
)

ROOT_RELS = (
    '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
    '<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">'
    '<Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="xl/workbook.xml"/>'
    "</Relationships>"
)

WORKBOOK = (
    '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
    '<workbook xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" '
    'xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships">'
    '<sheets><sheet name="Sheet1" sheetId="1" r:id="rId1"/></sheets>'
    "</workbook>"
)

WORKBOOK_RELS = (
    '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
    '<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">'
    '<Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet" Target="worksheets/sheet1.xml"/>'
    '<Relationship Id="rId2" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles" Target="styles.xml"/>'
    "</Relationships>"
)

STYLES = (
    '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'
    '<styleSheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main">'
    '<fonts count="1"><font><sz val="11"/><name val="Calibri"/></font></fonts>'
    '<fills count="1"><fill><patternFill patternType="none"/></fill></fills>'
    '<borders count="1"><border/></borders>'
    '<cellStyleXfs count="1"><xf/></cellStyleXfs>'
    '<cellXfs count="1"><xf xfId="0"/></cellXfs>'
    "</styleSheet>"
)


def main():
    ap = argparse.ArgumentParser(description="KFTC pipe-delimited text -> xlsx")
    ap.add_argument("input")
    ap.add_argument("output")
    ap.add_argument("--encoding", default="EUC-KR")
    ap.add_argument(
        "--fields",
        type=int,
        default=0,
        help="expected field count per line; 0 (default) = infer from the first line",
    )
    args = ap.parse_args()

    fields = args.fields
    rows = []
    with open(args.input, "r", encoding=args.encoding, newline="") as f:
        for lineno, raw in enumerate(f, 1):
            line = raw.rstrip("\r\n")
            if not line:
                continue
            tokens = line.split("|")
            if fields == 0:
                fields = len(tokens)
            if len(tokens) != fields:
                sys.exit(
                    "line %d: expected %d fields, found %d"
                    % (lineno, fields, len(tokens))
                )
            rows.append(tokens)

    if fields == 0:
        sys.exit("no data rows found in %s" % args.input)

    sheet = build_sheet(rows, fields)
    with zipfile.ZipFile(args.output, "w", zipfile.ZIP_DEFLATED) as z:
        z.writestr("[Content_Types].xml", CONTENT_TYPES)
        z.writestr("_rels/.rels", ROOT_RELS)
        z.writestr("xl/workbook.xml", WORKBOOK)
        z.writestr("xl/_rels/workbook.xml.rels", WORKBOOK_RELS)
        z.writestr("xl/styles.xml", STYLES)
        z.writestr("xl/worksheets/sheet1.xml", sheet)

    print("wrote %s (%d data rows, %d fields)" % (args.output, len(rows), fields))


if __name__ == "__main__":
    main()
