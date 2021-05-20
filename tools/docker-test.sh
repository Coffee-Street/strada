#!/bin/bash

#Setup properties
set -ex

export BASE_DIR=$(pwd)
export TOOLS_DIR="$BASE_DIR/tools"
export STRADA_MYSQL_HOST="127.0.0.1"
export STRADA_MYSQL_PORT=3306
export MIGRATION_INITDB=$TOOLS_DIR/migration

echo $BASE_DIR
echo $TOOLS_DIR

# Migration mysql
mysql -V

for sql_file in $MIGRATION_INITDB/*
do
  echo $sql_file
  mysql -h $STRADA_MYSQL_HOST -uroot test_integration_db < $sql_file
done
echo "Mysql migration is end"



# Gradle
chmod +x gradlew

GRADLE_OPT=""
GRADLE_OPT="$GRADLE_OPT -Dspring.datasource.url=jdbc:mysql://mysql:3306/test_integration_db?useLegacyDatetimeCode=false&serverTimezone=GMT&useUnicode=true"
GRADLE_OPT="$GRADLE_OPT --stacktrace"
./gradlew $GRADLE_OPT clean test
