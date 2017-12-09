inherit gnusources

DESCRIPTION = "GCC for building linux kernel header files."

DEPENDS = "mpc-cross mpfr-cross isl-cross gmp-cross binutils-cross"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "677a7623c7ef6ab99881bc4e048debb6"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
B = "${WORKDIR}/gcc.${BUILD}.${TARGET}"

do_configure () {
  export PATH="${TARGET_INSTALL_DIR}/bin:${BUILD_INSTALL_DIR}/bin:$PATH"
  ${S}/configure \
		--target=${TARGET} \
		--prefix=${TARGET_INSTALL_DIR} \
		--without-headers \
    --with-newlib \
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

do_compile () {
  export PATH="${TARGET_INSTALL_DIR}/bin:${BUILD_INSTALL_DIR}/bin:$PATH"
  runmake ${MAKE_JX} all-gcc all-target-libgcc
}

do_install () {
  export PATH="${TARGET_INSTALL_DIR}/bin:${BUILD_INSTALL_DIR}/bin:$PATH"
  runmake install-gcc install-target-libgcc
}
