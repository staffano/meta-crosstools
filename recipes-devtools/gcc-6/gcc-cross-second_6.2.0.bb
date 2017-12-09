inherit gnusources

DESCRIPTION = "GCC for building final GLIBC"

DEPENDS = "mpc-cross mpfr-cross isl-cross gmp-cross binutils-cross libc-for-gcc"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "9768625159663b300ae4de2f4745fcc4"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
B = "${WORKDIR}/gcc.${BUILD}.${TARGET}"

do_configure () {
  ${S}/configure \
		--target=${TARGET} \
		--prefix=${TARGET_INSTALL_DIR} \
		--without-headers \
    --with-sysroot=${TARGET_SYSROOT} \
		--disable-shared \
		--disable-threads \
		--disable-libssp \
		--disable-libgomp \
		--disable-libmudflap \
		--disable-libquadmath \
		--disable-libatomic \
    --with-mpc=${TARGET_INSTALL_DIR} \
  	--with-mpfr=${TARGET_INSTALL_DIR} \
  	--with-gmp=${TARGET_INSTALL_DIR}  \
  	--with-isl=${TARGET_INSTALL_DIR} \
		--with-host-libstdcxx="-lstdc++" \
      ${ADDITIONAL_GCC_CONF} \
		--enable-languages=c
}
