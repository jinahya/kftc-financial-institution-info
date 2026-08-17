# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)

[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.jinahya/kftc-financial-institution-info)](https://central.sonatype.com/artifact/io.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/io.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/io.github.jinahya/kftc-financial-institution-info)

[//]: # ([![Website]&#40;https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr&label=%EA%B8%88%EC%9C%B5%EA%B2%B0%EC%A0%9C%EC%9B%90&#41;]&#40;https://www.kftc.or.kr&#41;)
[//]: # ([![Website]&#40;https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr%2Farchive%2FbankListByCode&label=%EA%B8%88%EC%9C%B5%ED%9A%8C%EC%82%AC%EC%BD%94%EB%93%9C%EC%A1%B0%ED%9A%8C&#41;]&#40;https://www.kftc.or.kr/archive/bankListByCode&#41;)

A simple, no-deps, library for accessing financial institution information provided
by [KFTC](https://www.kftc.or.kr/archive/bankListByCode)(금융결제원, 金融決濟院, Korea Financial Telecommunications
and Clearings Institute).

<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc-refresh-toc -->
**Table of Contents**

  - [What this is](#what-this-is)
  - [What you can do with it](#what-you-can-do-with-it)
  - [Apache Maven Coordinates](#apache-maven-coordinates)
    - [Versioning](#versioning)
  - [Data](#data)
  - [API](#api)
    - [`KftcFinancialInstitutionInfo`](#kftcfinancialinstitutioninfo)
    - [`KftcFinancialInstitutionBranchInfo`](#kftcfinancialinstitutionbranchinfo)
  - [Usages](#usages)
    - [금융 기관 정보](#금융-기관-정보)
      - [001 / 한국은행](#001--한국은행)
      - [101 / 한국신용정보원](#101--한국신용정보원)
      - [Filtering and sorting](#filtering-and-sorting)
    - [금융 기관 지점 정보](#금융-기관-지점-정보)
      - [0010003 / 한국 / 본부총괄](#0010003--한국--본부총괄)
      - [4920018 / 중소벤처기업진흥공단 / 성장융합금융처](#4920018--중소벤처기업진흥공단--성장융합금융처)
    - [Notes](#notes)
  - [More output formats](#more-output-formats)
    - [SQLite DB file](#sqlite-db-file)
    - [JSON](#json)
    - [[NDJSON](https://github.com/ndjson/ndjson-spec)](#ndjsonhttpsgithubcomndjsonndjson-spec)
    - [Protocol Buffers](#protocol-buffers)
  - [Build environment](#build-environment)
    - [JDK](#jdk)
    - [Build commands](#build-commands)
  - [License](#license)
  - [Links](#links)
    - [www.kftc.or.kr](#wwwkftcorkr)
    - [issues.apache.org](#issuesapacheorg)
    - [jakarta.ee](#jakartaee)
    - [garrit.xyz](#garritxyz)
    - [stackoverflow.com](#stackoverflowcom)
    - [HWP PDF 변환](#httpswwwpolarisofficetoolshwpconvertpdf)

<!-- markdown-toc end -->


---

## What this is

KFTC publishes the official Korean financial institution codes (금융회사코드) and branch codes as downloadable HWP, PDF,
Excel and fixed-width text files. Parsing those by hand, in every project that needs them, is tedious and easy to get
wrong.

This library does that parsing once, at build time, and ships the result as plain Java objects.

* **Zero runtime dependencies** — every dependency in the `pom.xml` is `test`-scoped.
* **Offline** — the data is bundled in the jar; nothing is fetched, and no network or database is involved.
* **Immutable & thread-safe** — every model and every collection view is unmodifiable.
* **Java 11+**, with a JPMS module descriptor (`com.github.jinahya.kftc.financial.institution.info`).

It is *not* a client for any KFTC service, and it does not do payments, account verification, or code validation
against a live registry. It is a lookup table with a typed API over a point-in-time snapshot.

---

## What you can do with it

* Resolve a 3-digit **institution code** to its name and category — e.g. render `"004"` as `국민은행` in a UI, or
  validate that a user-supplied bank code exists.
* Enumerate institutions and **filter or sort** them — e.g. build a bank picker listing only 대표 codes, grouped by
  category.
* Resolve a 7-digit **branch code** to its branch name, address, postal code, phone and fax number.
* Get KFTC's padded free-text fields **normalized** — the raw data preserves the source column padding, so a phone
  number may read `"02  759 4114"`; the `*Normalized` accessors collapse each run of consecutive whitespace into a
  replacement of your choice, giving `"02 759 4114"`, `"02-759-4114"` or `"0232115603"`.
* Tell whether a branch is 정상 or 잠정폐쇄, as raw text or as an enum constant.
* Take the same data **out of Java entirely** — the build can emit it as an SQLite database, JSON, NDJSON or Protocol
  Buffers. See [More output formats](#more-output-formats).

---

## Apache Maven Coordinates

```xml

<dependency>
  <groupId>io.github.jinahya</groupId>
  <artifactId>kftc-financial-institution-info</artifactId>
  <!-- Check the badge above for the latest version -->
</dependency>
```

### Versioning

Release versions carry a build-metadata suffix (`+yyyyMMdd`) holding the **date of the KFTC data snapshot** bundled in
that release. A new release is cut whenever KFTC publishes updated data, even when no code has changed — so prefer the
newest version for the freshest data.

---

## Data

Two datasets are bundled as serialized resources inside the jar:

| dataset                        | resource        | key                  |
|--------------------------------|-----------------|----------------------|
| 금융 기관 정보 (institutions)  | `bankinfo.ser`  | 3-digit `code`       |
| 금융 기관 지점 정보 (branches) | `codefilex.ser` | 7-digit `branchCode` |

There are a few hundred institutions, and tens of thousands of branches.

Institutions are classified by [`KftcFinancialInstitutionCategory`](src/main/java/com/github/jinahya/kftc/financial/institution/info/KftcFinancialInstitutionCategory.java):

| constant | 분류         |
|----------|--------------|
| `BANK`   | 은행         |
| `FIIN`   | 금융투자회사 |
| `CAPI`   | 캐피탈사     |
| `CARD`   | 카드사       |
| `INSU`   | 보험사       |
| `OTHE`   | 기타         |

Several institution codes may share a name; `isRepresentative()` marks the 대표 entry for each.

---

## API

Two collection classes are the entry points. Each is created with a `newInstance()` static factory method, and exposes
an unmodifiable `list()` (source order) and an unmodifiable `map()` (keyed by code).

| class                                   | element                              | `map()` key  |
|-----------------------------------------|--------------------------------------|--------------|
| `KftcFinancialInstitutionInfoSet`       | `KftcFinancialInstitutionInfo`       | `code`       |
| `KftcFinancialInstitutionBranchInfoSet` | `KftcFinancialInstitutionBranchInfo` | `branchCode` |

The `getList()` / `getMap()` bean-style aliases are deprecated for removal; use `list()` / `map()`.

### `KftcFinancialInstitutionInfo`

| accessor             | type                               | description                        |
|----------------------|------------------------------------|------------------------------------|
| `getCode()`          | `String`                           | 3-digit 금융기관 코드              |
| `getName()`          | `String`                           | 금융기관 이름                      |
| `getCategory()`      | `KftcFinancialInstitutionCategory` | 분류                               |
| `isRepresentative()` | `boolean`                          | whether the entry is the 대표 code |

`KftcFinancialInstitutionInfo.COMPARING_CODE` is a ready-made `Comparator` ordering by `code`.

### `KftcFinancialInstitutionBranchInfo`

| accessor                           | type     | description                                              |
|------------------------------------|----------|----------------------------------------------------------|
| `getBranchCode()`                  | `String` | 7-digit 지점 코드                                        |
| `getFinancialInstitutionName()`    | `String` | 금융기관 이름                                            |
| `getBranchName()`                  | `String` | 지점 이름                                                |
| `getPhoneNumber()`                 | `String` | 전화번호, **as-is** (may contain consecutive whitespace) |
| `getPhoneNumberNormalized(String)` | `String` | 전화번호, consecutive whitespace replaced                |
| `getFaxNumber()`                   | `String` | 팩스번호, as-is                                          |
| `getFaxNumberNormalized(String)`   | `String` | 팩스번호, consecutive whitespace replaced                |
| `getPostalCode()`                  | `String` | 우편번호                                                 |
| `getAddress()`                     | `String` | 주소, as-is                                              |
| `getAddressNormalized(String)`     | `String` | 주소, consecutive whitespace replaced                    |
| `getStatus()`                      | `String` | `"정상"` or `"잠정폐쇄"`                                 |
| `getManagingBranchCode()`          | `String` | 관리점 코드, nullable                                    |

The raw `status` text can be mapped onto the nested `KftcFinancialInstitutionBranchInfo.Status` enum
(`ACTIVE` / `TEMPORARILY_CLOSED`):

```java
final var status = KftcFinancialInstitutionBranchInfo.Status.valueOfRawValue(info.getStatus());
```

---

## Usages

The snippets below are excerpted from
[`Readme1Test`](src/test/java/com/github/jinahya/kftc/financial/institution/info/Readme1Test.java) and
[`Readme2Test`](src/test/java/com/github/jinahya/kftc/financial/institution/info/Readme2Test.java), which run as part
of the build.

### 금융 기관 정보

#### 001 / 한국은행

```java
final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
final var info = infoSet.map().get("001");
assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
assert info.getCode().equals("001");
assert info.getName().equals("한국은행");
assert info.isRepresentative();
```

#### 101 / 한국신용정보원

```java
final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
final var info = infoSet.map().get("101");
assert info.getCategory() == KftcFinancialInstitutionCategory.OTHE;
assert info.getCode().equals("101");
assert info.getName().equals("한국신용정보원");
assert info.isRepresentative();
```

#### Filtering and sorting

```java
final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
infoSet.list().stream()
        .filter(i -> i.getCategory() == KftcFinancialInstitutionCategory.BANK)
        .filter(KftcFinancialInstitutionInfo::isRepresentative)
        .sorted(KftcFinancialInstitutionInfo.COMPARING_CODE)
        .forEach(i -> System.out.printf("%s %s%n", i.getCode(), i.getName()));
```

### 금융 기관 지점 정보

#### 0010003 / 한국 / 본부총괄

```java
final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
final var info = infoSet.map().get("0010003");
assert info.getBranchCode().equals("0010003");
assert info.getFinancialInstitutionName().equals("한국");
assert info.getBranchName().equals("본부총괄");
assert info.getPhoneNumber().equals("02  759 4114");           // mind the consecutive whitespace
assert info.getPhoneNumberNormalized(" ").equals("02 759 4114");
assert info.getPhoneNumberNormalized("-").equals("02-759-4114");
assert info.getFaxNumber().equals("02  759 4060");             // mind the consecutive whitespace
assert info.getFaxNumberNormalized(" ").equals("02 759 4060");
assert info.getFaxNumberNormalized("-").equals("02-759-4060");
assert info.getPostalCode().equals("100794");
assert info.getAddress().equals("서울특별시 중구 남대문로 39");
assert info.getStatus().equals("정상");
assert info.getManagingBranchCode() == null;
```

#### 4920018 / 중소벤처기업진흥공단 / 성장융합금융처

```java
final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
final var info = infoSet.map().get("4920018");
assert info.getBranchCode().equals("4920018");
assert info.getFinancialInstitutionName().equals("중소벤처기업진흥공단");
assert info.getBranchName().equals("성장융합금융처");
assert info.getPhoneNumber().equals("02  32115603");           // mind the consecutive whitespace
assert info.getPhoneNumberNormalized(" ").equals("02 32115603");
assert info.getPhoneNumberNormalized("").equals("0232115603");
assert info.getFaxNumber().equals("0505047 4412");
assert info.getPostalCode().equals("52851");
assert info.getAddress().equals("경상남도 진주시 동진로 430 (충무공동) 중소벤처기업진흥공단");
assert info.getStatus().equals("정상");
assert info.getManagingBranchCode() == null;
```

### Notes

Those `newInstance()` methods, whenever invoked, load data from resources in the classpath. Callers are recommended to (
or should) cache the result.

```java
class KftcService {

    // a fairly small set
    // the set and its values are all immutable and thread-safe
    public static final KftcFinancialInstitutionInfoSet INFO_SET
            = KftcFinancialInstitutionInfoSet.newInstance();

    // a much larger one!
    // do not load if it's not required 
    public static final KftcFinancialInstitutionBranchInfoSet BRANCH_INFO_SET
            = KftcFinancialInstitutionBranchInfoSet.newInstance();
}
```

---

## More output formats

The same data can be emitted in several formats for use outside Java — seeding a database, feeding a service in
another language, or just inspecting it with `jq`. These are build artifacts, not published dependencies; run the
matching profile to produce them.

### SQLite DB file

You can generate an SQLite database file, while builds, into `db/kftc-financial-institution-info.sqlite3`.

```shell
$ mvn -Pdb process-test-resources test
$ ls -l db
```

See [kftc-financial-institution-info.sqlite3.md](db/kftc-financial-institution-info.sqlite3.md) for more information.


### JSON

Produces `bankinfo.json` / `codefilex.json` (compact) alongside `*_formatted.json` (pretty-printed).

```commandline
$ mvn -Pjson test
$ ls -l target/*.json
```

```json
{"category":"BANK","code":"001","name":"한국은행","representative":true}
```

### [NDJSON](https://github.com/ndjson/ndjson-spec)

One JSON object per line — convenient for streaming and for `jq`.

```commandline
$ mvn -Pndjson test
$ ls -l target/*.ndjson
```

### Protocol Buffers

Produces `*.binpb` (binary), `*.jsonpb` (JSON mapping) and `*.txtpb` (text format). The `.proto` definitions live in
[`src/test/resources-proto`](src/test/resources-proto); see [PROTO.md](PROTO.md) for how they are compiled.

```commandline
$ mvn -Pproto test
$ ls -l target/*.*pb
```

---

## Build environment

### JDK

Requires `25` for building while the module targets `11`.

<!-- $ grep maven.compiler\\. pom.xml -->

```xml
<!-- $ grep '<maven.compiler' pom.xml -->
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
    <maven.compiler.release>${maven.compiler.target}</maven.compiler.release>
    <maven.compiler.testSource>25</maven.compiler.testSource>
    <maven.compiler.testTarget>${maven.compiler.testSource}</maven.compiler.testTarget>
    <maven.compiler.testRelease>${maven.compiler.testTarget}</maven.compiler.testRelease>
```

### Build commands

```shell
# build and test
$ mvn clean test

# a single test class
$ mvn test -Dtest=KftcFinancialInstitutionInfo_Test

# every output-format profile at once
$ mvn -Pdb,json,ndjson,proto clean process-test-resources test
```

The bundled `.ser` resources are regenerated from the raw KFTC source files in `src/test/resources` as part of the
test run; `./_refresh.sh` downloads a fresh set of those source files and drives the whole refresh.

Test sources are split per profile: `src/test/java` (always), plus `src/test/java-db`, `src/test/java-json`,
`src/test/java-ndjson` and `src/test/java-proto`, each added by its own profile.

The `pom.xml` layout is enforced by `tidy-maven-plugin` during `validate`; run `mvn tidy:pom` before committing.

---

## License

Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).

The bundled data originates from [KFTC](https://www.kftc.or.kr/archive/bankListByCode) and is redistributed here as a
convenience; refer to KFTC for its authoritative and current form.

---

## Links

### www.kftc.or.kr

* [금융회사코드 조회](https://www.kftc.or.kr/archive/bankListByCode) (금융결재원)


### issues.apache.org

* [\[MJAVADOC-728\] Dependencies not resolved for aggregate-jar
  ](https://issues.apache.org/jira/browse/MJAVADOC-728)
* [\[MRESOURCES-237\] Resource plugin's handling of symbolic links changed in 3.0.x, broke existing behavior](https://issues.apache.org/jira/browse/MRESOURCES-237)

### jakarta.ee

* [Jakarta Persistence XML Schemas](https://jakarta.ee/xml/ns/persistence/)

### garrit.xyz

* [Tracking SQLite Database Changes in Git](https://garrit.xyz/posts/2023-11-01-tracking-sqlite-database-changes-in-git)
    * [Git hook for diff sqlite table](https://stackoverflow.com/a/21789167/330457)

### stackoverflow.com

* [Maven Resources Plugin symbolic link handling](https://stackoverflow.com/q/40346225/330457)




### https://www.polarisofficetools.com/hwp/convert/pdf
