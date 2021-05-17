#!/bin/bash

#Setup properties
set -ex

echo "start"

pwd
ls

export BASE_DIR="/"
export TOOLS_DIR="$BASE_DIR/tools"

# Determine redis host, port
if [ -z $STRADA_MYSQL_HOST ]; then
  export STRADA_MYSQL_HOST=mysql
fi
if [ -z $STRADA_MYSQL_PORT ]; then
  export STRADA_MYSQL_PORT=3306
fi

chmod +x gradlew
./gradlew clean build