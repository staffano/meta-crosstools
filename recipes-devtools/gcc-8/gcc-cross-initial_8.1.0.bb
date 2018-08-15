require gcc-common_${PV}.inc

DEPENDS = "mpc mpfr isl gmp binutils"

B = "${WORKDIR}/${BUILD}.${TARGET}"

do_configure () {
  ${S}/configure \
	--build=${BUILD} \
	--host=${HOST} \
	--target=${TARGET} \
	--prefix=${INSTALL_DIR} \
	--without-headers \
	--with-newlib \
	--disable-shared \
	--disable-threads \
	--disable-libgomp \
	--disable-libssp \
	--disable-libatomic \
	--disable-libitm \
	--disable-libsanitizer \
	--disable-libquadmath \
	--disable-libvtv \
	--disable-libcilkrts \
	--disable-libstdc++-v3 \
	--with-mpc=${INSTALL_DIR} \
	--with-mpfr=${INSTALL_DIR} \
	--with-gmp=${INSTALL_DIR}  \
	--with-isl=${INSTALL_DIR} \
	--with-glibc-version=2.25 \
	${ADDITIONAL_GCC_CONF} \
	--enable-languages=c
}

do_compile () {
  runmake ${MAKE_JX} all-gcc all-target-libgcc
}

do_install () {
  runmake install-gcc install-target-libgcc
}
