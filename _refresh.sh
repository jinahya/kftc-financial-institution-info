#!/bin/sh
# #%L
# kftc-financial-institution-info
# %%
# Copyright (C) 2024 - 2025 Jinahya, Inc.
# %%
# Licensed under the Apache License, Version 2.0 (the "License").
# #L%
#
# Refreshes the bundled KFTC data after new source files have been downloaded.
# Automates every step of _WORKFLOW.md that does not require a GUI:
#
#   1. verify the 6 downloaded files exist
#   2. regenerate codefilex.text.xlsx / codechgfile.text.xlsx from the .text files
#      (replaces the manual Excel "Get Data / Text/CSV / EUC-KR" import)
#   3. regenerate _sha1sum.txt
#   4. mvn clean test   -> rebuilds src/main/resources/.../*.ser from the sources
#      (bankinfo.ser is read straight from bankinfo.hwp via hwplib -- no PDF print)
#   5. full multi-profile verification build
#   6. report what changed in the committed data and print tag/deploy next steps
#
# By default it downloads the 6 files itself (headless browser, see
# tools/kftc-download), so you don't have to.
#
# Usage:
#   sh ./_refresh.sh                # download the 6 files, refresh, verify
#   sh ./_refresh.sh --no-download  # use the files already in src/test/resources
#   sh ./_refresh.sh --no-build     # only regenerate xlsx + checksums (skip Maven)
set -eu

ROOT=$(cd "$(dirname "$0")" && pwd)
RES="$ROOT/src/test/resources"
cd "$ROOT"

RUN_BUILD=1
DOWNLOAD=1
for arg in "$@"; do
    case "$arg" in
        --no-build) RUN_BUILD=0 ;;
        --no-download) DOWNLOAD=0 ;;
        *) printf 'unknown option: %s\n' "$arg" >&2; exit 2 ;;
    esac
done

say() { printf '\n==> %s\n' "$1"; }
die() { printf 'ERROR: %s\n' "$1" >&2; exit 1; }

# --- 0. (optional) download the 6 source files via headless browser -----------
if [ "$DOWNLOAD" -eq 1 ]; then
    say "Downloading the 6 source files (headless browser, real clicks)"
    command -v node >/dev/null 2>&1 || die "node is required for --download (https://nodejs.org)"
    DL_DIR="$ROOT/tools/kftc-download"
    [ -d "$DL_DIR/node_modules" ] || ( cd "$DL_DIR" && npm install )
    ( cd "$DL_DIR" && node download.mjs --out "$RES" ) \
        || die "download failed -- existing source files left untouched"
fi

# --- 1. preconditions ---------------------------------------------------------
say "Checking source files in src/test/resources"
DOWNLOADED="bankinfo.hwp bankinfo.pdf codefilex.text codefilex.xls codechgfile.text codechgfile.xls"
for f in $DOWNLOADED; do
    [ -s "$RES/$f" ] || die "missing/empty: $f -- download all 6 files from https://www.kftc.or.kr/archive/bankListByCode into src/test/resources first"
done

# --- 2. regenerate xlsx from the EUC-KR text files ----------------------------
say "Regenerating .xlsx from .text (EUC-KR) -- replaces the manual Excel step"
python3 "$RES/_textToXlsx.py" "$RES/codefilex.text"   "$RES/codefilex.text.xlsx"
python3 "$RES/_textToXlsx.py" "$RES/codechgfile.text" "$RES/codechgfile.text.xlsx"

# --- 3. checksums -------------------------------------------------------------
say "Regenerating _sha1sum.txt"
( cd "$RES" && sh ./_sha1.sh )

if [ "$RUN_BUILD" -eq 0 ]; then
    say "Skipping Maven build (--no-build). Done."
    exit 0
fi

# --- 4. rebuild the .ser resources (and run the core unit tests) --------------
say "mvn clean test  (regenerates bankinfo.ser / codefilex.ser from the sources)"
mvn -q clean test

# --- 5. full verification build across all output-format profiles -------------
say "Full verification build  (-Pdb,json,ndjson,proto)"
mvn -q -Pdb,json,ndjson,proto clean process-test-resources test

# --- 6. report ----------------------------------------------------------------
say "Refresh complete. Changes to committed data:"
git --no-pager status --short -- src/main/resources src/test/resources || true

cat <<'EOF'

Next steps (manual, after reviewing the diff above):
  git add -A
  git commit -m "data: refresh KFTC financial institution data"
  git push

  # Release: maven-release-plugin (from jinahya-parent) creates the tag and deploys.
  # Versions are date-based (e.g. 1.0.0.20260205); accept the defaults it proposes:
  mvn release:prepare release:perform
EOF
