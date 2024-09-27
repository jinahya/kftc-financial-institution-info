#!/bin/sh
sqlite3 kftc-financial-institution-info.sqlite3 'VACUUM;'
echo Ctrl+D to quit
sqlite3 kftc-financial-institution-info.sqlite3
