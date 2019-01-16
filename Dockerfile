FROM ubuntu

# Specify the version of bitbake to use by setting --build-arg BITBAKE_VERSION=1.38.1
# Use 1.38.0 if not specified

ARG BITBAKE_VERSION
ENV BITBAKE_VERSION ${BITBAKE_VERSION:-1.38.0}
RUN apt update -y && apt upgrade -y
RUN apt install -y build-essential gnat git locales python3 wget m4 gawk unzip nano texinfo rsync python3-distutils
RUN apt install -y pkg-config
RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
RUN mkdir -p /meta-crosstools/build/
RUN cd /meta-crosstools/build && wget https://github.com/openembedded/bitbake/archive/$BITBAKE_VERSION.zip && unzip $BITBAKE_VERSION.zip

COPY classes /meta-crosstools/classes
COPY conf /meta-crosstools/conf
COPY recipes-devtools /meta-crosstools/recipes-devtools
COPY recipes-buildtools /meta-crosstools/recipes-buildtools
COPY recipes-gnat /meta-crosstools/recipes-gnat
COPY recipes-libs /meta-crosstools/recipes-libs
COPY scripts /meta-crosstools/scripts

VOLUME /meta-crosstools/build/downloads
VOLUME  /meta-crosstools/build/tmp
WORKDIR /meta-crosstools/build/
ENTRYPOINT ["python3", "/meta-crosstools/scripts/build.py"]
