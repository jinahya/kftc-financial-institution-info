# kftc-financial-institution-info

[![Java CI with Maven](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/kftc-financial-institution-info/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_kftc-financial-institution-info&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_kftc-financial-institution-info)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.github.jinahya/kftc-financial-institution-info)](https://search.maven.org/artifact/com.github.jinahya/kftc-financial-institution-info)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/kftc-financial-institution-info/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/kftc-financial-institution-info)

Financial institution information, provided by [KFTC](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do).

금융결재원에서 제공하는 금융기관들에 대한 정보를 조회할 수 있는 API이다.

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
assert info != null;
assert info.getCode().equals("001");
assert info.getName().equals("한국은행");
```

```java
// 금융기관 지점 정보 조회
final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
final var info = instance.get("0010003").orElseThrow();
assert info != null;
assert info.getBranchCode().equals("0010003");
assert info.getFinancialInstitutionName().equals("한국");
```

---

## Links

### www.kftc.org.kr

* [금융회사코드 조회](https://www.kftc.or.kr/kftc/data/EgovBankListMove.do) (금융결재원)

### issues.apache.org

* https://issues.apache.org/jira/browse/MJAVADOC-728
