# kftc-download

Headless-browser downloader for the six KFTC source files used by this library.

The page <https://www.kftc.or.kr/archive/bankListByCode> is a JavaScript app with no
static download URLs; each file is fetched by a client-side call whose server-side
file name changes on every upload. So instead of reverse-engineering the API, this
tool drives a headless Chromium and **clicks the real download buttons** — exactly
what a human does — which resolves the dynamic names automatically.

## Usage

```sh
npm install                 # one-time: installs playwright + chromium
node download.mjs           # downloads into ../../src/test/resources
node download.mjs --out DIR # custom target dir
node download.mjs --headed  # show the browser (debugging)
```

Normally you don't run this directly — use `sh ./_refresh.sh --download` from the
repo root, which calls this and then runs the rest of the refresh pipeline.

## Safety

Files are downloaded to a temp staging dir and each is validated (size + format:
HWP/PDF magic, EUC-KR field counts for the `.text` files, HTML for the `.xls`
files). Only if **all six** pass are they moved into the target dir, so a broken
scrape (e.g. after a KFTC site redesign) can never overwrite known-good files.

The button → filename mapping lives in `download.mjs` (`FILES`). If KFTC changes the
button labels, update the `aria` strings there.
