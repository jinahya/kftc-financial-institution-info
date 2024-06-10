# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-info)

A simple, zero-dependency, library provides financial institution information, provided
by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do).

## Latest date (down)loaded from the KFTC

| date         | info      | hash                               | notes |
|--------------|-----------|------------------------------------|-------|
| `2024-06-03` | bankinfo  | `b1db0be8aa26f73eba6609f0b1b25f34` |       |
|              | codefilex | `b642a224f50a06fa19bd1c32f0d61565` |       |

---

## Build environment

### JDK

Requires `21` for building while the module targets `11`.

```text
$ grep maven.compiler\\. pom.xml
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
  <maven.compiler.release>${maven.compiler.target}</maven.compiler.release>
  <maven.compiler.testSource>21</maven.compiler.testSource>
  <maven.compiler.testTarget>${maven.compiler.testSource}</maven.compiler.testTarget>
  <maven.compiler.testRelease>${maven.compiler.testTarget}</maven.compiler.testRelease>
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
class ReadmeTest {

    @Test
    void __1() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = instance.get("001").orElseThrow();
        assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
        assert Objects.equals(info.getCode(), "001");
        assert Objects.equals(info.getName(), "한국은행");
        assert info.isRepresentative();
    }

    @Test
    void __2() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = instance.get("094").orElseThrow();
        assert info.getCategory() == KftcFinancialInstitutionCategory.MISC;
        assert info.getCode().equals("094");
        assert info.getName().equals("서울보증보험");
        assert info.isRepresentative();
    }

    @Test
    void __3() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = instance.get("0010003").orElseThrow();
        assert Objects.equals(info.getBranchCode(), "0010003");
        assert Objects.equals(info.getFinancialInstitutionName(), "한국");
        assert Objects.equals(info.getBranchName(), "본부총괄");
        assert Objects.equals(info.getPhoneNumber(), "02  759 4114"); // mind multiple spaces
        assert Objects.equals(info.getFaxNumber(), "02  759 4060");   // mind multiple spaces
        assert Objects.equals(info.getPostalCode(), "100794");
        assert Objects.equals(info.getAddress(), "서울특별시 중구 남대문로 39");
        assert Objects.equals(info.getStatus(), "정상");
        assert info.getManagingBranchCode() == null;
    }

    @Test
    void __4() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = instance.get("4920018").orElseThrow();
        assert info.getBranchCode().equals("4920018");
        assert info.getFinancialInstitutionName().equals("중소벤처기업진흥공단");
        assert info.getBranchName().equals("성장융합금융처");
        assert info.getPhoneNumber().equals("02  32115603");
        assert info.getFaxNumber().equals("0505047 4412");
        assert info.getPostalCode().equals("52851");
        assert info.getAddress().equals("경상남도 진주시 동진로 430 (충무공동) 중소벤처기업진흥공단");
        assert info.getStatus().equals("정상");
        assert info.getManagingBranchCode() == null;
    }
}
```

---

## More output formats.

### SQLite DB file

You can generate an SQLite database file, while builds, into `db/kftc-financial-institution-info.sqlite3`.

```shell
$ mvn -Pdb clean test
```

See [kftc-financial-institution-info.sqlite3.md](db/kftc-financial-institution-info.sqlite3.md) for more information.

---

## Links

### www.kftc.org.kr

* [금융회사코드 조회](https://www.kftc.or.kr/archive/bankListByCode) (금융결재원)

### issues.apache.org

* [\[MJAVADOC-728\] Dependencies not resolved for aggregate-jar
  ](https://issues.apache.org/jira/browse/MJAVADOC-728)
* [\[MRESOURCES-237\] Resource plugin's handling of symbolic links changed in 3.0.x, broke existing behavior](https://issues.apache.org/jira/browse/MRESOURCES-237)

### garrit.xyz

* [Tracking SQLite Database Changes in Git](https://garrit.xyz/posts/2023-11-01-tracking-sqlite-database-changes-in-git)
    * [Git hook for diff sqlite table
      ](https://stackoverflow.com/a/21789167/330457)

### stackoverflow.com

* [Maven Resources Plugin symbolic link handling](https://stackoverflow.com/q/40346225/330457)
