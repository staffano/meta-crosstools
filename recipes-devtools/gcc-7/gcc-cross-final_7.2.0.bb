require gcc-common_${PV}.inc

# Depends on native versions of libs, since the compiler itself will run
# nativels
DEPENDS = "binutils gmp mpfr isl mpc libc"

PROVIDES += "virtual/final-gcc"

B = "${WORKDIR}/${BUILD}.${TARGET}"


CONFIG_OPTS = " \
	--build=${BUILD} \
  	--host=${HOST} \
  	--target=${TARGET} \
	--with-sysroot=${SYSROOT_DIR} \
  	--disable-nls \
  	--disable-multilib \
  	--prefix=${INSTALL_DIR} \
  	--with-mpc=${INSTALL_DIR} \
  	--with-mpfr=${INSTALL_DIR} \
  	--with-gmp=${INSTALL_DIR}  \
  	--with-isl=${INSTALL_DIR} \
  	--enable-languages=${LANGUAGES} \
  	--enable-fully-dynamic-string \
	${ADDITIONAL_GCC_CONF} \
"

CONFIG_OPTS_remove_newlib = " --with-sysroot=${SYSROOT_DIR} "
CONFIG_OPTS_append_newlib = " --with-newlib --disable-libada "

CONFIG_OPTS_append_nothreads = " --disable-threads "
CONFIG_OPTS_append_posixthreads = " --enable-threads=posix "


do_configure () {
${S}/configure ${CONFIG_OPTS}

}

PNAME = "${BUILD}.${HOST}.${TARGET}.tar.gz"

do_install_append_sysroot-in-toolchain () {
	rsync -a ${SYSROOT_DIR}/${TARGET}/ ${INSTALL_DIR}/${TARGET}/
}

do_install_append () {
	cd ${INSTALL_DIR}
	tar czf "../${PNAME}" .
	cd ..
	mv ${PNAME} ${TOOLCHAINS}
}
