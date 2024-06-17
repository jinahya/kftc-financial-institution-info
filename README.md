# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-info)

[![Website](https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr&label=%EA%B8%88%EC%9C%B5%EA%B2%B0%EC%A0%9C%EC%9B%90)](https://www.kftc.or.kr)
[![Website](https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr%2Farchive%2FbankListByCode&label=%EA%B8%88%EC%9C%B5%ED%9A%8C%EC%82%AC%EC%BD%94%EB%93%9C%EC%A1%B0%ED%9A%8C)](https://www.kftc.or.kr/archive/bankListByCode)

A simple, zero-dependency, library for accessing financial institution information provided
by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do).

## Latest date (down)loaded from the KFTC

| date         | info           | hash                               | notes      |
|--------------|----------------|------------------------------------|------------|
| `2024-06-17` | bankinfo.hwp   | `5a2c09f97b5c31bf6756fe74c075549e` | 금융기관 정보    |
|              | codefilex.text | `bad482f4b9a61ddf2fc7e3f1fc94e46d` | 금융기관 지점 정보 |
| `2024-06-10` | bankinfo.hwp   | `5a2c09f97b5c31bf6756fe74c075549e` | 금융기관 정보    |
|              | codefilex.text | `b7cf392a32f1a0dd5b66f419ebfc5eac` | 금융기관 지점 정보 |

---

## Build environment

### JDK

Requires `21` for building while the module targets `11`.

<!-- $ grep maven.compiler\\. pom.xml -->

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

### Notes

This module generates resource files while testing which are, in turn, required by some other tests, hence, a fresh
clone might need to be build twice. And that's
why [The Postman Always Rings Twice](https://en.wikipedia.org/wiki/The_Postman_Always_Rings_Twice_(1981_film)).

<!-- https://serverfault.com/a/737269/113357 -->

```commandline
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

### 금융 기관 정보

```java
class Readme1Test {

    @Test
    void __001() {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = infoSet.get("001");
        assertThat(info).hasValueSatisfying(i -> {
            assertThat(i.getCategory()).isSameAs(KftcFinancialInstitutionCategory.BANK);
            assertThat(i.getCode()).isEqualTo("001");
            assertThat(i.getName()).isEqualTo("한국은행");
            assertThat(i.isRepresentative()).isTrue();
        });
    }

    @Test
    void __101() {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = infoSet.get("101");
        assertThat(info).hasValueSatisfying(i -> {
            assertThat(i.getCategory()).isSameAs(KftcFinancialInstitutionCategory.OTHE);
            assertThat(i.getCode()).isEqualTo("101");
            assertThat(i.getName()).isEqualTo("한국신용정보원");
            assertThat(i.isRepresentative()).isTrue();
        });
    }
}
```

### 금융 기관 지점 정보

```java
class Readme2Test {

    @Test
    void __3() {
        final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = infoSet.get("0010003").orElseThrow();
        assert Objects.equals(info.getBranchCode(), "0010003");
        assert Objects.equals(info.getFinancialInstitutionName(), "한국");
        assert Objects.equals(info.getBranchName(), "본부총괄");
        assert Objects.equals(info.getPhoneNumber(), "02  759 4114"); // mind multiple spaces
        assert Objects.equals(info.getPhoneNumberNormalized(" "), "02 759 4114");
        assert Objects.equals(info.getPhoneNumberNormalized("-"), "02-759-4114");
        assert Objects.equals(info.getFaxNumber(), "02  759 4060");   // mind multiple spaces
        assert Objects.equals(info.getFaxNumberNormalized(" "), "02 759 4060");
        assert Objects.equals(info.getFaxNumberNormalized("-"), "02-759-4060");
        assert Objects.equals(info.getPostalCode(), "100794");
        assert Objects.equals(info.getAddress(), "서울특별시 중구 남대문로 39");
        assert Objects.equals(info.getStatus(), "정상");
        assert info.getManagingBranchCode() == null;
    }

    @Test
    void __4() {
        final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = infoSet.get("4920018").orElseThrow();
        assert info.getBranchCode().equals("4920018");
        assert info.getFinancialInstitutionName().equals("중소벤처기업진흥공단");
        assert info.getBranchName().equals("성장융합금융처");
        assert info.getPhoneNumber().equals("02  32115603"); // mind multiple spaces
        assert info.getPhoneNumberNormalized(" ").equals("02 32115603");
        assert info.getPhoneNumberNormalized("").equals("0232115603");
        assert info.getFaxNumber().equals("0505047 4412");
        assert info.getPostalCode().equals("52851");
        assert info.getAddress().equals("경상남도 진주시 동진로 430 (충무공동) 중소벤처기업진흥공단");
        assert info.getStatus().equals("정상");
        assert info.getManagingBranchCode() == null;
    }
}
```

### Notes

Those `newInstance()` methods, whenever invoked, load data from resources in the classpath. Callers are recommended to (
or should) cache the result.

```java
class KftcService {

    // less than two hundreds values
    // the set and its values are all immutable and thread-safe
    public static final KftcFinancialInstitutionInfoSet INFO_SET
            = KftcFinancialInstitutionInfoSet.newInstance();

    // more than 20 thousands values!
    // do not load at all, if it's not required 
    public static final KftcFinancialInstitutionBranchInfoSet BRANCH_INFO_SET
            = KftcFinancialInstitutionBranchInfoSet.newInstance();
}
```

---

## More output formats

### SQLite DB file

You can generate an SQLite database file, while builds, into `db/kftc-financial-institution-info.sqlite3`.

```shell
$ mvn -Pdb clean test
$ ls -l db
```

See [kftc-financial-institution-info.sqlite3.md](db/kftc-financial-institution-info.sqlite3.md) for more information.

### Protocol Buffers

Two `.proto` files are
prepared. One
is [KftcFinancialInstitutionBranchInfoProto.proto](src/test/resources-proto/KftcFinancialInstitutionBranchInfoProto.proto)
and the other is
[KftcFinancialInstitutionInfoProto.proto](src/test/resources-proto/KftcFinancialInstitutionInfoProto.proto).

```commandline
$ sh ./.protoc.sh
$ ls -l src/test/java-proto/com/github/jinahya/kftc/financial/institution/info/proto/*ProtoOuterClass.java
$ mvn -Pproto test
$ ls -l target/*.*pb
```

### JSON

```commandline
$ mvn -Pjson test
$ ls -l target/*.json
```

### [JSON Lines](https://jsonlines.org/)

```commandline
$ mvn -Pjsonl test
$ ls -l target/*.jsonl
```

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
    * [Git hook for diff sqlite table](https://stackoverflow.com/a/21789167/330457)

### stackoverflow.com

* [Maven Resources Plugin symbolic link handling](https://stackoverflow.com/q/40346225/330457)
