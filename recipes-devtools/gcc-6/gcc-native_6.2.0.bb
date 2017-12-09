inherit gnusources

DESCRIPTION = "gcc for native"

DEPENDS = "gmp-native mpfr-native isl-native mpc-native binutils-native"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "9768625159663b300ae4de2f4745fcc4"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
B = "${WORKDIR}/gcc.${BUILD}.${BUILD}"

do_configure () {

  ${S}/configure \
  	--build=${BUILD} \
  	--host=${BUILD} \
  	--target=${BUILD} \
  	--disable-nls \
  	--disable-multilib \
  	--prefix=${BUILD_INSTALL_DIR} \
  	--with-mpc=${BUILD_INSTALL_DIR} \
  	--with-mpfr=${BUILD_INSTALL_DIR} \
  	--with-gmp=${BUILD_INSTALL_DIR}  \
  	--with-isl=${BUILD_INSTALL_DIR} \
  	--enable-languages=c,c++,ada \
  	--enable-threads=posix \
  	--enable-fully-dynamic-string
}
