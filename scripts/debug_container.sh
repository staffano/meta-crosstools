#!/bin/bash
docker run --entrypoint /bin/bash --rm -it \
    -v bb-download:/meta-crosstools/build/downloads \
    -v bb-tmp:/meta-crosstools/build/tmp \
    mcb:latest
