# kftc-financial-institution-codes

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-codes/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-codes/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-codes&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-codes)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-codes)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-codes)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-codes/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-codes)

Codes, assigned by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do), for financial institutions.

* [금융회사코드 조회](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do)


## Build environment

### JDK

```shell
$ grep "<maven.compiler." pom.xml
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
    <maven.compiler.release>${maven.compiler.target}</maven.compiler.release>
    <maven.compiler.testSource>21</maven.compiler.testSource>
    <maven.compiler.testTarget>${maven.compiler.testSource}</maven.compiler.testTarget>
    <maven.compiler.testRelease>${maven.compiler.testTarget}</maven.compiler.testRelease>
```

### Apache Maven

```xml
<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>kftc-financial-institution-codes</artifactId>
</dependency>
```

## Usages

```java
final var code = KftcFinancialInstitutionCodes.getCodeList().stream()
        .filter(c -> c.getCode().equals("001"))
        .findFirst()
        .orElse(null);
assert code != null;
assert code.getCode().equals("001");
assert code.getName().equals("한국은행");
```
```java
final var code = KftcFinancialInstitutionCodes.getCodeList().stream()
        .filter(c -> c.getName().equals("산업은행") && c.isRepresentative())
        .findFirst()
        .orElse(null);
assert code != null;
assert code.getCode().equals("002");
assert code.getName().equals("산업은행");
```
```java
KftcFinancialInstitutionCodes.getCodeList().stream()
        .filter(c -> c.getCategory() == Category.INSU) // 보험사
        .forEach(c -> {
            log.debug("code: {}", c);
        });
```
