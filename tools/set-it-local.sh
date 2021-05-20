#!/bin/bash

export STRADA_MYSQL_HOST="localhost"
export STRADA_MYSQL_PORT="3306"
CURRENT_DIR=$(cd $(dirname $0); pwd)
echo "CURRENT_DIR: $CURRENT_DIR"
export MIGRATION_INITDB=/api/src/main/resources/db/migration

docker-compose -f $CURRENT_DIR/docker-compose-integration.local.yml up -d
