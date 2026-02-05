# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A zero-runtime-dependency Java library for accessing financial institution information provided by [KFTC](https://www.kftc.or.kr/archive/bankListByCode) (금융결제원). Published to Maven Central as `io.github.jinahya:kftc-financial-institution-info`.

## Build Requirements

- **JDK 25** (main library targets Java 11; tests compile at Java 25)
- **Maven 3.6.3+**
- **Gradle wrapper** is included (used only for protobuf compilation)

## Build Commands

```bash
# Standard build and test
mvn clean test

# Single test class
mvn test -Dtest=KftcFinancialInstitutionInfo_Test

# With specific profile(s)
mvn -Pdb process-test-resources test     # SQLite DB generation
mvn -Pjson test                          # JSON output
mvn -Pndjson test                        # NDJSON output
mvn -Pproto test                         # Protocol Buffers

# All profiles at once
mvn -Pdb,json,ndjson,proto clean process-test-resources test

# Full build script (runs tests 2x, then with all profiles)
sh ./.mvn.build.sh
```

## Architecture

### Core Models (src/main/java)

The library provides two main immutable, serializable, thread-safe models:

- **`KftcFinancialInstitutionInfo`** — A financial institution (~200 entries). Key: `code` (3-digit string).
- **`KftcFinancialInstitutionBranchInfo`** — A branch location (~20,000+ entries). Key: `branchCode` (7-digit string).
- **`KftcFinancialInstitutionCategory`** — Enum: BANK, FIIN, CAPI, CARD, INSU, OTHE.

Collection classes (`KftcFinancialInstitutionInfoSet`, `KftcFinancialInstitutionBranchInfoSet`) load data from serialized `.ser` resources via `newInstance()`. They expose `List` and `Map` views.

Internal utilities use underscore-prefix naming (`_StringUtils`, `_IoUtils`, `_Info`, `_InfoSet`).

### Data Flow

Raw data files (HWP, PDF, Excel, text) in `src/test/resources/` → tests parse and serialize → `bankinfo.ser` and `codefilex.ser` in `src/main/resources/` → deserialized at runtime by the library.

### Test Source Layout

Tests are split across directories activated by Maven profiles:

| Directory | Profile | Purpose |
|-----------|---------|---------|
| `src/test/java/` | (always) | Core unit tests |
| `src/test/java-db/` | `db` | JPA/Hibernate persistence to SQLite |
| `src/test/java-json/` | `json` | Jackson JSON serialization |
| `src/test/java-ndjson/` | `ndjson` | NDJSON output |
| `src/test/java-proto/` | `proto` | Generated protobuf classes + tests |

Each profile uses `build-helper-maven-plugin` to add its `java-*` and `resources-*` directories.

### Protobuf / Gradle Integration

Proto definitions live in `src/test/resources-proto/`. The `proto` Maven profile invokes Gradle via `exec-maven-plugin` to run `./gradlew generateTestProto`, which compiles `.proto` files and copies generated Java sources to `src/test/java-proto/`. Generated files are committed to the repo.

Gradle files: `_build.gradle` (underscore prefix to avoid Maven conflicts), `settings.gradle`, `gradle.properties`, `gradlew`. Gradle home is isolated to `.gradle_home/`.

## Key Conventions

- **Test naming**: `ClassName_Feature_Test.java`, nested `@Test` classes for grouping
- **Internal classes**: underscore prefix (`_Info`, `_InfoSet`, `_StringUtils`)
- **No runtime dependencies**: all dependencies are test-scoped
- **JPMS module**: `com.github.jinahya.kftc.financial.institution.info`
- **pom.xml formatting**: enforced by `tidy-maven-plugin` during `validate` phase — run `mvn tidy:pom` to auto-format before committing

## Important Rules

- Do **not** change parts of `pom.xml` outside the specific section you're working on (profiles are modular — respect boundaries)
- Gradle files (`_build.gradle`, `settings.gradle`, `gradle.properties`) can be freely modified
- The `protobufArtifactVersion` in `gradle.properties` must stay in sync with `version.com.google.protobuf` in `pom.xml`
