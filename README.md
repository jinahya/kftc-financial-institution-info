# kftc-financial-institution-codes

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-codes/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-codes/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-codes&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-codes)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-codes)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-codes)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-codes/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-codes)

Codes, assigned by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do), for financial institutions. 금융결재원에서 금융기관들에 할당한 코드와 정보를 조회할 수 있는 API이다.

## Links

* [금융회사코드 조회](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do) (금융결재원)


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

## Apache Maven Coordinates

```xml
<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>kftc-financial-institution-codes</artifactId>
  <!-- Check the badge above for the latest version -->
</dependency>
```

## Usages

```java
final var info = KftcFinancialInstitutionInfoSet.getInstance().get("001").orElseThrow();
assert info != null;
assert info.getCode().equals("001");
assert info.getName().equals("한국은행");
```

```java
final var info = KftcFinancialInstitutionBranchInfoSet.getInstance().get("0010003").orElseThrow();
assert info != null;
assert info.getBranchCode().equals("0010003");
assert info.getFinancialInstitutionName().equals("한국");
```
