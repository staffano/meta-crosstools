#!/bin/bash

# Use the minimal alpine image to create a container with the
# tmp volume attached
# Copy all the images into the host IMAGES folder
targets_unparsed=$(docker run --rm -v bb-tmp:/root alpine ls /root)
cid=$(docker container create --name dummy -v bb-tmp:/root alpine)
readarray -t targets <<< "$targets_unparsed"
for v in "${targets[@]}";do
    echo "Retrieving $v"
    docker cp dummy:/root/$v/IMAGES ./
done
cn=$(docker rm dummy)