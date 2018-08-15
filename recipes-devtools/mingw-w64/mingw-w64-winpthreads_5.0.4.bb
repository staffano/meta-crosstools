require mingw-w64-common.inc

DESCRIPTION = "MinGW-W64 version of winpthreads"
DEPENDS = "gcc-cross-final"

do_configure () {
	${S}/mingw-w64-libraries/winpthreads/configure \
		--build=${BUILD} \
   		--host=${TARGET} \
		--with-sysroot=${SYSROOT_DIR} \ 
		--prefix=${SYSROOT_DIR}
}
