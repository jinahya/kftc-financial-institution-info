// Headless-browser downloader for the KFTC bankListByCode source files.
//
// Loads https://www.kftc.or.kr/archive/bankListByCode in a headless Chromium and
// clicks the six real download buttons -- exactly what a human does -- so the
// dynamic server-side file names resolve themselves. Files are downloaded into a
// temporary staging dir, validated, and only moved into the target dir if ALL six
// pass. That way a broken scrape can never overwrite known-good committed files.
//
// Usage:
//   node download.mjs [--out <dir>] [--headed] [--keep-temp]
// Default --out is ../../src/test/resources relative to this script.

import { chromium } from 'playwright';
import fs from 'node:fs';
import os from 'node:os';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const HERE = path.dirname(fileURLToPath(import.meta.url));
const PAGE_URL = 'https://www.kftc.or.kr/archive/bankListByCode';

// --- args --------------------------------------------------------------------
const argv = process.argv.slice(2);
const getOpt = (name, def) => {
  const i = argv.indexOf(name);
  return i >= 0 && argv[i + 1] ? argv[i + 1] : def;
};
const OUT = path.resolve(getOpt('--out', path.join(HERE, '..', '..', 'src', 'test', 'resources')));
const HEADED = argv.includes('--headed');
const KEEP_TEMP = argv.includes('--keep-temp');

// exact aria-label on the page -> target filename + validator
const FILES = [
  { aria: '대표기관 및 기관코드 다운로드(한글파일)', name: 'bankinfo.hwp', check: isOle, minBytes: 10_000 },
  { aria: '대표기관 및 기관코드 다운로드(pdf파일)', name: 'bankinfo.pdf', check: isPdf, minBytes: 10_000 },
  // The "excel" downloads are actually HTML <table> documents saved with an .xls
  // extension (a common KR-gov pattern) -- not OLE/OOXML. No test consumes them.
  { aria: '전체코드 다운로드(엑셀)', name: 'codefilex.xls', check: isHtml, minBytes: 100_000 },
  { aria: '전체코드 다운로드(문서)', name: 'codefilex.text', check: euckrFields(9), minBytes: 100_000 },
  { aria: '최근접수내역(1개월) 다운로드(엑셀)', name: 'codechgfile.xls', check: isHtml, minBytes: 5_000 },
  { aria: '최근접수내역(1개월) 다운로드(문서)', name: 'codechgfile.text', check: euckrFields(10), minBytes: 1_000 },
];

// --- validators --------------------------------------------------------------
function isOle(buf) {
  // .hwp and .xls are OLE2 compound files: D0 CF 11 E0 A1 B1 1A E1
  const sig = [0xd0, 0xcf, 0x11, 0xe0, 0xa1, 0xb1, 0x1a, 0xe1];
  return sig.every((b, i) => buf[i] === b) || 'not an OLE2 (.hwp/.xls) file';
}
function isPdf(buf) {
  return buf.slice(0, 5).toString('latin1') === '%PDF-' || 'not a PDF file';
}
function isHtml(buf) {
  const head = buf.slice(0, 64).toString('latin1').toLowerCase().trimStart();
  return head.startsWith('<html') || head.startsWith('<!doctype html') || 'not an HTML (.xls) document';
}
function euckrFields(expected) {
  return (buf) => {
    let text;
    try {
      text = new TextDecoder('euc-kr', { fatal: true }).decode(buf);
    } catch {
      return 'not decodable as EUC-KR';
    }
    const lines = text.split(/\r?\n/).filter((l) => l.length > 0);
    if (lines.length < 1) return 'no data lines';
    for (let i = 0; i < lines.length; i++) {
      const n = lines[i].split('|').length;
      if (n !== expected) return `line ${i + 1}: expected ${expected} fields, found ${n}`;
    }
    return true;
  };
}

// --- main --------------------------------------------------------------------
const tmp = fs.mkdtempSync(path.join(os.tmpdir(), 'kftc-dl-'));
const log = (m) => process.stdout.write(m + '\n');
let browser;
try {
  log(`Launching headless Chromium -> ${PAGE_URL}`);
  browser = await chromium.launch({ headless: !HEADED });
  const ctx = await browser.newContext({ acceptDownloads: true });
  const page = await ctx.newPage();
  await page.goto(PAGE_URL, { waitUntil: 'networkidle', timeout: 60_000 });
  await page.waitForTimeout(2_500);

  const staged = [];
  for (const f of FILES) {
    const btn = page.locator(`button[aria-label="${f.aria}"]`);
    if ((await btn.count()) === 0) {
      throw new Error(`download button not found (page layout changed?): "${f.aria}"`);
    }
    const [dl] = await Promise.all([
      page.waitForEvent('download', { timeout: 60_000 }),
      btn.first().click(),
    ]);
    const dest = path.join(tmp, f.name);
    await dl.saveAs(dest);

    const buf = fs.readFileSync(dest);
    if (buf.length < f.minBytes) {
      throw new Error(`${f.name}: too small (${buf.length} < ${f.minBytes} bytes)`);
    }
    const ok = f.check(buf);
    if (ok !== true) throw new Error(`${f.name}: validation failed -- ${ok}`);
    log(`  ok  ${f.name.padEnd(18)} ${buf.length} bytes  (suggested: "${dl.suggestedFilename()}")`);
    staged.push(f.name);
  }

  // all six valid -> move into place atomically (overwrite committed files)
  fs.mkdirSync(OUT, { recursive: true });
  for (const name of staged) {
    fs.copyFileSync(path.join(tmp, name), path.join(OUT, name));
  }
  log(`\nAll ${staged.length} files validated and written to ${OUT}`);
} catch (err) {
  log(`\nERROR: ${err.message}`);
  log('No files were written (staging discarded); existing source files are untouched.');
  process.exitCode = 1;
} finally {
  if (browser) await browser.close();
  if (!KEEP_TEMP) fs.rmSync(tmp, { recursive: true, force: true });
}
