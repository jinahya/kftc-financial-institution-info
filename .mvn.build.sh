#!/bin/sh
seq 2 | xargs -I -- mvn clean test
sh ./.protoc.sh
mvn -Pdb,json,ndjson,proto clean test
