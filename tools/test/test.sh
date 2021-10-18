#!/bin/bash

## Setup properties
set -ex

export BASE_DIR=$(pwd)
export TOOLS_DIR="$BASE_DIR/tools"
export MIGRATION_INITDB="$TOOLS_DIR/migration"
export STRADA_MYSQL_HOST="127.0.0.1"
export STRADA_MYSQL_PORT="3306"


## Wait until mysql is up
while ! mysqladmin ping -h $STRADA_MYSQL_HOST -P $STRADA_MYSQL_PORT --silent ; do
  echo "."
  sleep 3
done
echo "Mysql is up"


## it test를 위한 sql을 작성해주세요
# Migration mysql
for sql_file in $MIGRATION_INITDB/*
do
  echo $sql_file
  mysql -h $STRADA_MYSQL_HOST -uroot test_integration_db < $sql_file
done
echo "Mysql migration is end"


## Gradle
chmod +x gradlew
GRADLE_OPT=""
./gradlew $GRADLE_OPT clean test detekt
