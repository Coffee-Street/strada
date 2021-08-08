#!/bin/bash
set -o verbose

# set variable
export CURRENT_DIR=$(dirname "$0")
export BASE_DIR="../$CURRENT_DIR"

# default options variable
PORT=8080
ACTIVE_PROFILE=default

# set options
function usage()
{
    cat << EOM
Usage: $0 [options]
Options:
 -p, --port            Specify container external port (default 8080)
 --active-profile      Specify spring active profile (default default)
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

# docker build
docker build --tag strada-api "${BASE_DIR}"

# docker deploy
docker run -d -p "${PORT}":8080 -e "${JAVA_OPTIONS}" strada-api
