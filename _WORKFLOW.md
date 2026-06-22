# Workflow

데이터 갱신은 `./_refresh.sh` **한 번**으로 끝난다. 수동 GUI 단계 없음
(예전의 HWP→PDF 인쇄, 엑셀 변환, 파일 수동 다운로드 모두 자동화됨).

## 1. 실행

```sh
sh ./_refresh.sh                # 다운로드 → 처리 → .ser 재생성 → 검증 빌드
sh ./_refresh.sh --no-download  # 이미 받아 둔 파일을 그대로 사용
sh ./_refresh.sh --no-build     # xlsx/체크섬만 다시 만들고 Maven 빌드는 건너뜀
```

예외 없이 끝나면 다 된 것이다. 이후 `git diff` 로 변경 확인 후 커밋/배포(§4).

## 2. `_refresh.sh` 가 하는 일

0. (기본) 헤드리스 브라우저(Playwright)가 <https://www.kftc.or.kr/archive/bankListByCode>
   의 다운로드 버튼을 실제로 클릭해 여섯 파일을 받아 형식 검증 후 `src/test/resources` 에
   덮어쓴다. (`tools/kftc-download`, 처음 한 번 `npm install` 필요.)
   * `bankinfo.hwp`, `bankinfo.pdf`, `codefilex.text`, `codefilex.xls`,
     `codechgfile.text`, `codechgfile.xls`
1. 여섯 파일이 있는지 확인한다.
2. `codefilex.text` / `codechgfile.text` (EUC-KR, `|` 구분) 를 `*.text.xlsx` 로 변환한다.
   * 예전의 "엑셀에서 Get Data / Text/CSV / EUC-KR 적재 후 저장" 수동 과정을 대체한다.
   * 변환기: `src/test/resources/_textToXlsx.py` (표준 라이브러리만 사용).
3. `src/test/resources/_sha1.sh` 로 `_sha1sum.txt` 를 다시 만든다.
4. `mvn clean test` — `bankinfo.ser` / `codefilex.ser` 를 다시 생성한다.
   * `bankinfo.ser` 는 `bankinfo.hwp` 를 **hwplib 로 직접** 읽어 만든다 (PDF 인쇄 불필요).
   * `codefilex.ser` 는 raw `codefilex.text` 에서 만든다.
5. `mvn -Pdb,json,ndjson,proto clean process-test-resources test` — 전체 프로파일 검증 빌드.
6. 바뀐 데이터(`*.ser`, `_sha1sum.txt`, `*.xlsx`)를 `git status` 로 보여 준다.

## 3. 문서가 처리된 뒤 만들어지는 것 (산출물)

처리된 문서(`bankinfo.hwp`, `codefilex.text`)로부터 빌드가 만들어 내는 결과물.

### 3-1. 핵심 산출물 — 라이브러리 데이터 (항상 생성)

테스트의 `*_ResourceGeneration_*Test` 가 문서를 파싱해서 두 개의 직렬화 리소스를 만든다.

* `src/main/resources/com/github/jinahya/kftc/financial/institution/info/bankinfo.ser`
  — 금융기관 정보(`KftcFinancialInstitutionInfo`, ~200개)
* `src/main/resources/com/github/jinahya/kftc/financial/institution/info/codefilex.ser`
  — 지점 정보(`KftcFinancialInstitutionBranchInfo`, ~2만+개)

이 둘이 **실제로 배포되는 산출물**이다. JAR(`io.github.jinahya:kftc-financial-institution-info`)
에 그대로 포함되고, 런타임에 `KftcFinancialInstitutionInfoSet.newInstance()` /
`KftcFinancialInstitutionBranchInfoSet.newInstance()` 가 클래스패스에서 읽어 들인다.
커밋되는 데이터 변경은 사실상 이 `.ser` 두 개다.

### 3-2. 부가 출력 포맷 (프로파일별, 필요할 때만)

같은 데이터를 다른 포맷으로도 내보낼 수 있다. 검증 빌드(`-Pdb,json,ndjson,proto`)는 아래를
모두 생성한다. (`db/*.sqlite3` 와 `target/` 는 `.gitignore` 처리 — 커밋되지 않음.)

| 프로파일 | 명령 | 출력 | 위치 |
|---------|------|------|------|
| `db`     | `mvn -Pdb process-test-resources test` | `kftc-financial-institution-info.sqlite3` (테이블 2개 + 인덱스, 스키마는 `db/*.sqlite3.md`) | `db/` |
| `json`   | `mvn -Pjson test`   | `bankinfo.json`, `bankinfo_formatted.json`, `codefilex.json`, `codefilex_formatted.json` | `target/` |
| `ndjson` | `mvn -Pndjson test` | `bankinfo.ndjson`, `codefilex.ndjson` | `target/` |
| `proto`  | `mvn -Pproto test`  | `bankinfo.binpb`, `codefilex.binpb` (생성된 Java 소스는 `src/test/java-proto/` 에 커밋) | `target/` |

## 4. 커밋 및 배포

`git diff` 로 `*.ser` 변경을 확인한 뒤:

```sh
git add -A && git commit -m "data: refresh KFTC financial institution data" && git push
mvn release:prepare release:perform   # 날짜 기반 태그(1.0.0.YYYYMMDD) 생성 + 배포
```

## 참고: 어떤 파일이 실제로 쓰이나

* 빌드에 실제로 소비되는 입력: `bankinfo.hwp`, `codefilex.text`, `codefilex.text.xlsx`
* `bankinfo.pdf`, `codefilex.xls`, `codechgfile.*` 는 체크섬 기록용일 뿐 테스트가 읽지 않는다.
