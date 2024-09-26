#!/bin/sh
seq 2 | xargs -I -- mvn clean test
sh ./.protoc.sh
mvn -Pjson,ndjson,proto clean test
mvn -Pdb process-test-resources test
