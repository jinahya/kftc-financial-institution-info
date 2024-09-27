# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)

[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/io.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/io.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/io.github.jinahya/kftc-financial-institution-info)

[![Website](https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr&label=%EA%B8%88%EC%9C%B5%EA%B2%B0%EC%A0%9C%EC%9B%90)](https://www.kftc.or.kr)
[![Website](https://img.shields.io/website?url=https%3A%2F%2Fwww.kftc.or.kr%2Farchive%2FbankListByCode&label=%EA%B8%88%EC%9C%B5%ED%9A%8C%EC%82%AC%EC%BD%94%EB%93%9C%EC%A1%B0%ED%9A%8C)](https://www.kftc.or.kr/archive/bankListByCode)

A simple, zero-dependency, library for accessing financial institution information provided
by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do)(금융결제원, 金融決濟院, Korea Financial Telecommunications
and Clearings Institute).

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

---

## Apache Maven Coordinates

```xml

<dependency>
  <groupId>io.github.jinahya</groupId>
  <artifactId>kftc-financial-institution-info</artifactId>
  <!-- Check the badge above for the latest version -->
</dependency>
```

---

## Usages

### 금융 기관 정보

https://github.com/jinahya/kftc-financial-institution-info/blob/0cd82778dfb142dd7fc51e5379a74bc8841991e7/src/test/java/com/github/jinahya/kftc/financial/institution/info/Readme1Test.java#L31-L53

### 금융 기관 지점 정보

https://github.com/jinahya/kftc-financial-institution-info/blob/0cd82778dfb142dd7fc51e5379a74bc8841991e7/src/test/java/com/github/jinahya/kftc/financial/institution/info/Readme2Test.java#L31-L65

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
$ mvn -Pdb process-test-resources test
$ ls -l db
```

See [kftc-financial-institution-info.sqlite3.md](db/kftc-financial-institution-info.sqlite3.md) for more information.


### JSON

```commandline
$ mvn -Pjson test
$ ls -l target/*.json
```

### [NDJSON](https://github.com/ndjson/ndjson-spec)

```commandline
$ mvn -Pndjson test
$ ls -l target/*.ndjson
```

### Protocol Buffers

Two `.proto` files are
prepared. One is [KftcFinancialInstitutionBranchInfoProto.proto](src/test/resources-proto/KftcFinancialInstitutionBranchInfoProto.proto) and the other is [KftcFinancialInstitutionInfoProto.proto](src/test/resources-proto/KftcFinancialInstitutionInfoProto.proto).

```commandline
$ sh ./.protoc.sh
$ ls -l src/test/java-proto/com/github/jinahya/kftc/financial/institution/info/proto/*ProtoOuterClass.java
$ mvn -Pproto test
$ ls -l target/*.*pb
```

---

## Links

### www.kftc.org.kr

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
