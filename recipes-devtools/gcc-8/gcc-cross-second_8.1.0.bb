require gcc-common_${PV}.inc

DEPENDS = "mpc mpfr isl gmp binutils libc-for-gcc"

B = "${WORKDIR}/${BUILD}.${TARGET}"

ADDITIONAL_GCC_CONF_append_newlib = "--with-newlib "

do_configure () {
  ${S}/configure \
		--build=${BUILD} \
		--host=${HOST} \
		--target=${TARGET} \
		--prefix=${INSTALL_DIR} \
		--without-headers \
		--with-sysroot=${SYSROOT_DIR} \
		--disable-shared \
		--disable-threads \
		--disable-libssp \
		--disable-libgomp \
		--disable-libmudflap \
		--disable-libquadmath \
		--disable-libatomic \
		--with-mpc=${INSTALL_DIR} \
		--with-mpfr=${INSTALL_DIR} \
		--with-gmp=${INSTALL_DIR}  \
		--with-isl=${INSTALL_DIR} \
		--with-host-libstdcxx="-lstdc++" \
		${ADDITIONAL_GCC_CONF} \
		--enable-languages=c
}
