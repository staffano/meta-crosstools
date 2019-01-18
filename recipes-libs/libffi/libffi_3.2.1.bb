DESCRIPTION = "A portable foreign-function interface library."
DEPENDS = "virtual/final-gcc"
PR = "r1"
SRC_URI = "\
      https://github.com/libffi/libffi/archive/v${PV}.tar.gz \
     "
SRC_URI[md5sum] = "9066486bcab807f7ddaaf2596348c1db"
LICENSE = "Other"

do_preconfigure() {
 	echo "XXX${S}XXX"
    cd ${S}
    ls -al
    ./autogen.sh
}
addtask preconfigure after do_unpack before do_configure

do_configure() { 
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} \
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}