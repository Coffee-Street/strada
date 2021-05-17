#!/bin/bash

#Setup properties
set -ex

export BASE_DIR=$(pwd)
export TOOLS_DIR="$BASE_DIR/tools"

echo $BASE_DIR
echo $TOOLS_DIR

# Determine mysql host, port
if [ -z $STRADA_MYSQL_HOST ]; then
  export STRADA_MYSQL_HOST=mysql
fi
if [ -z $STRADA_MYSQL_PORT ]; then
  export STRADA_MYSQL_PORT=3306
fi


# Gradle
chmod +x gradlew

GRADLE_OPT=""
GRADLE_OPT="$GRADLE_OPT --stacktrace"
./gradlew $GRADLE_OPT clean build
