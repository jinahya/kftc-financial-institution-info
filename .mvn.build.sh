#!/bin/sh
seq 2 | xargs -I -- mvn clean test
mvn -Pdb,json,ndjson,proto clean process-test-resources test
