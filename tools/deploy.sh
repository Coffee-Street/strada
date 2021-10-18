#!/bin/bash

# set variable
export CURRENT_DIR=$(dirname "$0")
export BASE_DIR="../$CURRENT_DIR"

# default options variable
PORT=8080
ACTIVE_PROFILE=default
DOCKER_DEPLOY=0

# set options
function usage()
{
    cat << EOM
Usage: $0 [options]
Options:
 -h, --help           Describe options
 -p, --port           Specify container external port (default 8080)
 --active-profile     Specify spring active profile
 --docker-deploy      Deploy with docker (0 or 1, default 0)
EOM
    exit 1
}

function set_options()
{
    while [ "${1:-}" != "" ]; do
        case "$1" in
            -p | --port)
                shift
                PORT=$1
                ;;
            --active-profile)
                shift
                ACTIVE_PROFILE=$1
                ;;
            --docker-deploy)
                shift
                DOCKER_DEPLOY=$1
                ;;
            *)
                usage
                ;;
        esac
        shift
    done
}
set_options "$@"

# set java options
JAVA_OPTIONS="
  -Djava.security.egd=file:/dev/./urandom \
  -Dspring.profiles.active=${ACTIVE_PROFILE} \
"

if [ "$DOCKER_DEPLOY" = "1" ]; then
  echo "docker deploy"

  # docker build & deploy
  docker build --tag strada-api "${BASE_DIR}"
  docker run -d -p "${PORT}":8080 -e "${JAVA_OPTIONS}" strada-api
else
  echo "node deploy"

  cp $BASE_DIR/api/build/libs/api*.jar $BASE_DIR/api/build/libs/service.jar
  java ${JAVA_OPTIONS} -jar $BASE_DIR/api/build/libs/service.jar
fi
