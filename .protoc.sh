#!/bin/sh
protoc --java_out=src/test/java-proto src/test/resources-proto/KftcFinancialInstitutionBranchInfo.proto
protoc --java_out=src/test/java-proto src/test/resources-proto/KftcFinancialInstitutionInfo.proto
