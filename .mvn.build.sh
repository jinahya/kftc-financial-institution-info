#!/bin/sh
seq 2 | xargs -I -- mvn clean test
mvn -Pdb clean test
