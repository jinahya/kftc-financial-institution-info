# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-info)

A simple library provides financial institution information, provided
by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do).

금융결재원에서 제공하는 금융기관 정보를 조회할 수 있는 API입니다. 변경이 필요하다고 판단되면 이슈를 올려주세요.

---

## Build environment

### JDK

Requires `21` for building while the module targets `11`.

```xml

<project>
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
  <maven.compiler.release>${maven.compiler.target}</maven.compiler.release>
  <maven.compiler.testSource>21</maven.compiler.testSource>
  <maven.compiler.testTarget>${maven.compiler.testSource}</maven.compiler.testTarget>
  <maven.compiler.testRelease>${maven.compiler.testTarget}</maven.compiler.testRelease>
</project>
```

---

## Apache Maven Coordinates

```xml

<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>kftc-financial-institution-info</artifactId>
  <!-- Check the badge above for the latest version -->
</dependency>
```

---

## Usages

```java
// 금융기관 정보 조회
final var instance = KftcFinancialInstitutionInfoSet.newInstance();

final var info = instance.get("001").orElseThrow();
assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
assert info.getCode().equals("001");
assert info.getName().equals("한국은행");
assert info.isRepresentative();
```

```java
// 금융기관 지점 정보 조회
final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();

final var info = instance.get("0010003").orElseThrow();
assert info.getBranchCode().equals("0010003");
assert info.getFinancialInstitutionName().equals("한국");
assert info.getBranchName().equals("본부총괄");
assert info.getPhoneNumber().equals("02  759 4114"); // mind multiple spaces
assert info.getFaxNumber().equals("02  759 4060");   // mind multiple spaces
assert info.getPostalCode().equals("100794");
assert info.getAddress().equals("서울특별시 중구 남대문로 39");
assert info.getStatus().equals("정상");
assert info.getManagingBranchCode() == null;
```

---

## SQLite db file

Two tables are created in `db/kftc-financial-institution-info.sqlite3` file.

### `financial_institution`

| name             | type      | description | notes |
|------------------|-----------|-------------|-------|
| `code`           | `TEXT`    | 금융기관 코드     |       |
| `name`           | `TEXT`    | 금융기관 이름     |       |
| `representative` | `INTEGER` | 대표 여부       |       |
| `category`       | `TEXT`    | 대표 여부       |       |

### `financial_institution_branch`

| name                         | type   | description | notes     |
|------------------------------|--------|-------------|-----------|
| `branch_code`                | `TEXT` | 지점 코드       |           |
| `financial_institution_name` | `TEXT` | 금융기관 이름     |           |
| `branch_name`                | `TEXT` | 지점 이름       |           |
| `phone_number`               | `TEXT` | 전화번호        |           |
| `fax_number`                 | `TEXT` | 팩스번호        |           |
| `postal_code`                | `TEXT` | 우편번호        |           |
| `address`                    | `TEXT` | 주소          |           |
| `status`                     | `TEXT` | 상태          | e.g. `정상` |
| `managing_branch_code`       | `TEXT` | 관리지점 코드     |           |

---

## Links

### www.kftc.org.kr

* [금융회사코드 조회](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do) (금융결재원)

### issues.apache.org

* https://issues.apache.org/jira/browse/MJAVADOC-728
