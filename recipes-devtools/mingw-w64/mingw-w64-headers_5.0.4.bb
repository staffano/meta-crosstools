require mingw-w64-common.inc

DESCRIPTION = "MinGW-W64 Headers"
PROVIDES += "libc-for-gcc"

do_configure () {
	${S}/mingw-w64-headers/configure \
		--build=${BUILD} \
   		--host=${TARGET} \
		--prefix=${SYSROOT_DIR}/${TARGET}
}

do_compile[noexec] = "1"

do_install () {
 	runmake install
	cd ${SYSROOT_DIR}
	ln -s x86_64-w64-mingw32 mingw
	cd ${TARGET}
	mkdir -p lib
	ln -s lib lib64
}
