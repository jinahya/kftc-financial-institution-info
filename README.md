# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-info)

A simple library provides financial institution information, provided
by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do).

## Latest date loaded from the KFTC

* 2024-05-14

---

## Build environment

### JDK

Requires `21` for building while the module targets `11`.

```shell
$ grep maven.compiler\\. pom.xml
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
  <maven.compiler.release>${maven.compiler.target}</maven.compiler.release>
  <maven.compiler.testSource>21</maven.compiler.testSource>
  <maven.compiler.testTarget>${maven.compiler.testSource}</maven.compiler.testTarget>
  <maven.compiler.testRelease>${maven.compiler.testTarget}</maven.compiler.testRelease>
```

### Apache Maven

This module, abnormally and weirdly, generates main resources while testing. That's why *The Postman Always Rings Twice*.

```shell
$ seq 2 | xargs -I -- mvn clean test
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
static final var instance = KftcFinancialInstitutionInfoSet.newInstance();

final var info = instance.get("001").orElseThrow();
assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
assert info.getCode().equals("001");
assert info.getName().equals("한국은행");
assert info.isRepresentative();
```

```java
// 금융기관 지점 정보 조회
static final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();

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

You can generate an SQLite database file, while builds, into `db/kftc-financial-institution-info.sqlite3`.


```shell
$ mvn -Pdb clean test
```

See `db/kftc-financial-institution-info.sqlite3.md` for more information.

---

## Links

### www.kftc.org.kr

* [금융회사코드 조회](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do) (금융결재원)

### issues.apache.org

* https://issues.apache.org/jira/browse/MJAVADOC-728

* [Resource plugin's handling of symbolic links changed in 3.0.x, broke existing behavior](https://issues.apache.org/jira/browse/MRESOURCES-237)

### garrit.xyz 

* [Tracking SQLite Database Changes in Git](https://garrit.xyz/posts/2023-11-01-tracking-sqlite-database-changes-in-git)
  * [Git hook for diff sqlite table
    ](https://stackoverflow.com/a/21789167/330457)

### stackoverflow.com

* [https://stackoverflow.com/q/40346225/330457](https://stackoverflow.com/q/40346225/330457)
