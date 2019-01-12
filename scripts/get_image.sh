#!/bin/bash

docker build -q -t nothing - <<EOF 
FROM scratch
CMD
EOF

docker container create --name dummy -v bb-tmp:/meta-crosstools/build/tmp nothing
docker cp dummy:/meta-crosstools/build/tmp/$TargetName/IMAGES ./
docker rm dummy