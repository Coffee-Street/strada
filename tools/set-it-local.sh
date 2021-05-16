#!/bin/bash

export MYSQL_HOST="localhost"
export MYSQL_PORT="3306"
CURRENT_DIR=$(cd $(dirname $0); pwd)
echo "CURRENT_DIR: $CURRENT_DIR"
export MIGRATION_INITDB=/api/src/main/resources/db/migration

docker-compose -f $CURRENT_DIR/docker-compose-integration.yml up -d


## Wait until mysql is up
# TODO: Set MySQL Admin Environment
# brew install mysql-client
#while ! mysqladmin ping -h $MYSQL_HOST -P $MYSQL_PORT --silent ; do
#  echo "."
#  sleep 3
#done
#echo "Mysql is up"

## Migration mysql
# TODO: Set MySQL Client Environment
#for sql_file in $MIGRATION_INITDB/*
#do
#  echo $sql_file
#  mysql -h $MYSQL_HOST -uroot < $sql_file
#done
#echo "Mysql migration is end"
