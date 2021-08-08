#!/bin/bash
set -o verbose

# get base directory
export CURRENT_DIR=$(dirname "$0")
export BASE_DIR="../$CURRENT_DIR"

# ready gradle build
chmod +x $BASE_DIR/gradlew
GRADLE_OPT="bootjar"
GRADLE_OPT="$GRADLE_OPT -p $BASE_DIR/api"

# start gradle build
echo "build start"
$BASE_DIR/gradlew $GRADLE_OPT
