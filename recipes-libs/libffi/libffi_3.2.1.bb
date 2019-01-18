DESCRIPTION = "A portable foreign-function interface library."
DEPENDS = "virtual/final-gcc"
PR = "r1"
SRC_URI = "\
      https://github.com/libffi/libffi/archive/v${PV}.tar.gz \
     "
SRC_URI[md5sum] = "9066486bcab807f7ddaaf2596348c1db"
LICENSE = "Other"

do_configure() { 
    pushd  ${S} 
    ./autogen.sh 
    popd
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} \
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}