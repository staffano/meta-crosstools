inherit gnusources

DESCRIPTION = "GCC for target"

DEPENDS = "gmp-cross mpfr-cross isl-cross mpc-cross libc"


PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "677a7623c7ef6ab99881bc4e048debb6"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
B = "${WORKDIR}/gcc.${BUILD}.${TARGET}"

do_configure () {

  ${S}/configure \
  	--build=${BUILD} \
  	--host=${HOST} \
  	--target=${TARGET} \
    --with-sysroot=${TARGET_SYSROOT} \
  	--disable-nls \
  	--disable-multilib \
  	--prefix=${TARGET_INSTALL_DIR} \
  	--with-mpc=${TARGET_INSTALL_DIR} \
  	--with-mpfr=${TARGET_INSTALL_DIR} \
  	--with-gmp=${TARGET_INSTALL_DIR}  \
  	--with-isl=${TARGET_INSTALL_DIR} \
  	--enable-languages=c,c++,ada \
  	--enable-threads=posix \
      ${ADDITIONAL_GCC_CONF} \
  	--enable-fully-dynamic-string
}
