#!/bin/sh
seq 2 | xargs -I -- mvn clean test
sh ./.protoc.sh
mvn -Pdb,proto,json,ndjson,jsonl clean test
