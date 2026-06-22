---
name: refresh-kftc-data
description: Refresh the bundled KFTC financial-institution data from newly downloaded source files, rebuild the .ser resources, run the full verification build, and prepare a release. Use when the user wants to update/refresh the KFTC data, regenerate bankinfo.ser/codefilex.ser, or cut a new data release. Implements _WORKFLOW.md.
---

# Refresh KFTC data

Automates the data-refresh pipeline described in `_WORKFLOW.md`. Two steps are GUI-only
and stay manual (download, HWP→PDF); everything else is scripted by `_refresh.sh`.

## Inputs (checkpoints)

1. **The 6 source files** from <https://www.kftc.or.kr/archive/bankListByCode>:
   `bankinfo.hwp`, `bankinfo.pdf`, `codefilex.text`, `codefilex.xls`,
   `codechgfile.text`, `codechgfile.xls`. By default `sh ./_refresh.sh` downloads
   these automatically (headless browser clicks the real download buttons; see
   `tools/kftc-download`), validating each file's format before overwriting so a
   broken scrape can't ship stale data. Use `--no-download` to reuse files the user
   already placed in `src/test/resources/`.
2. **`bankinfo.hwp.pdf`** — open `bankinfo.hwp` in Hancom and print/export it to
   `bankinfo.hwp.pdf`. This stays manual (GUI-only). The raw `bankinfo.pdf` is 2-up
   and the parser misreads it; only `bankinfo.hwp.pdf` is consumed. After a
   `--download` where `bankinfo.hwp` changed, the script stops and requires this
   re-print before it will build.

If inputs are missing, stop and ask the user — do not fabricate data.

## Steps

1. **Run the pipeline:**
   ```bash
   sh ./_refresh.sh                # download the 6 files, then refresh
   sh ./_refresh.sh --no-download  # use files already in src/test/resources
   ```
   This: (optionally downloads) validates inputs → regenerates `codefilex.text.xlsx` / `codechgfile.text.xlsx`
   from the EUC-KR `.text` files (replacing the manual Excel import) → regenerates
   `_sha1sum.txt` → `mvn clean test` (rewrites `src/main/resources/.../bankinfo.ser`
   and `codefilex.ser`) → full `-Pdb,json,ndjson,proto` verification build.

   Use `sh ./_refresh.sh --no-build` to only regenerate the xlsx + checksums.

2. **Interpret the result.** If Maven fails, the most likely cause is the HWP→PDF
   print: a layout change can break `KftcFinancialInstitution_InfoSet_ResourceGeneration_Test`
   (PDF text parsing) or a field-count change can break the BranchInfoSet test
   (`codefilex.text` must stay 9 pipe-delimited fields per line). Report the failing
   test and the relevant assertion; don't silently regenerate around it.

3. **Show the data diff.** Summarize `git status`/`git diff --stat` for the `.ser`
   files and `_sha1sum.txt`. A refresh with no `.ser` change means upstream data was
   identical — tell the user (a release may be unnecessary).

4. **Commit & release** (only when the user confirms the diff looks right):
   ```bash
   git add -A && git commit -m "data: refresh KFTC financial institution data" && git push
   mvn release:prepare release:perform   # date-based tag (1.0.0.YYYYMMDD) + deploy
   ```
   `release:prepare release:perform` requires deploy credentials and a clean tree;
   if the user only wants the tag, `release:prepare` alone creates and pushes it.

## Notes

- The `.ser` files are written into the **source tree** (`src/main/resources/...`) by
  the resource-generation tests, then packaged into the jar — that is by design.
- `bankinfo.pdf`, `codefilex.xls`, and all `codechgfile.*` files are kept only for the
  checksum manifest; no test consumes them. Only `bankinfo.hwp.pdf`, `codefilex.text`,
  and `codefilex.text.xlsx` actually drive the build.
- Do not edit `pom.xml` profile boundaries while refreshing data (see CLAUDE.md).
