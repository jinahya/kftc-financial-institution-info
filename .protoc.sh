#!/bin/sh
protoc --java_out=src/test/java-proto src/test/resources-proto/KftcFinancialInstitutionBranchInfoProto.proto
protoc --java_out=src/test/java-proto src/test/resources-proto/KftcFinancialInstitutionInfoProto.proto
