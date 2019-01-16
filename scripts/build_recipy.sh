#!/bin/bash

# Set working dir to where Dockerfile is located
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
pushd "$DIR/.."

# Parse arguments
POS_ARGS=()
while [[ $# -gt 0 ]]
do
key="$1"
case $key in
    -f|--force-rebuild)
    FORCE_REBUILD=YES
    shift
    ;;
    -v|--verbose)
    VERBOSE=YES
    shift
    ;;
    -c|--clean)
    CLEAN=YES
    shift
    ;;
    --default)
    DEFAULT=YES
    shift
    ;;
    *) # Others => Positional arguments
    POS_ARGS+=("$1") 
    shift 
    ;;
esac
done
RECIPE=${POS_ARGS[0]}
TARGET=${POS_ARGS[1]}

PROXY_VARS=(http_proxy HTTP_PROXY https_proxy HTTPS_PROXY \
    FTP_PROXY ftp_proxy NO_PROXY no_proxy)

PROXY_ARGS=
# set_proxy_args will find which of the PROXY_VARS that are set in the
# environment and create a string of arguments of the form
# $1 arg1=$arg1 $1 arg2=$arg2 ...
function set_proxy_args() {
    prefix_arg=$1
    PROXY_ARGS=
    for v in "${PROXY_VARS[@]}";do
        if [ ${!v} ]; then
            PROXY_ARGS+="$prefix_arg ${v}=${!v} "
        fi
    done
}

if [ $FORCE_REBUILD ] || [ -z "$(docker images mcb:latest -q)" ]
then
    # Get proxy args for building
    set_proxy_args "--build-arg"
    echo $PROXY_ARGS
    docker build $PROXY_ARGS -t mcb .
fi

# Create volumes if they don't exist
dv=$(docker volume ls -q)
if [[ ! $dv == *"bb-tmp"* ]]
then
    docker volume create bb-tmp    
fi
if [[ ! $dv == *"bb-downloads"* ]]
then
    docker volume create bb-downloads    
fi

# Build switchlist
switches=()

if [ $CLEAN ]; then
    switches+="--clean"
fi

if [ $VERBOSE ]; then
    switches+="--verbosity"
fi

# Execute command
# Get proxy args for running
set_proxy_args "--env"
docker run --rm -it $PROXY_ARGS -v bb-download:/meta-crosstools/build/downloads -v bb-tmp:/meta-crosstools/build/tmp mcb:latest $switches $RECIPE $TARGET

# Restore location where we were before script
popd
