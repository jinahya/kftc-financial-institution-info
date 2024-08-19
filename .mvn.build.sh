#!/bin/sh
seq 2 | xargs -I -- mvn clean test
mvn -Pdb,proto,json,ndjson,jsonl clean test
