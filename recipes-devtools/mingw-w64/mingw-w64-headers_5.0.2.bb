inherit gnusources

# cf. https://sourceforge.net/p/mingw-w64/wiki2/Cross%20Win32%20and%20Win64%20compiler/
DESCRIPTION = "MinGW-W64 Headers"

# Reuse pokys namein scheme
PROVIDES += "libc-for-gcc"

PR = "r1"
SRC_URI = "\
   http://downloads.sourceforge.net/project/mingw-w64/mingw-w64/mingw-w64-release/mingw-w64-v${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "80d6884c9da234e73054347f44158b8a"
LICENSE = "GPLv3"

S = "${WORKDIR}/mingw-w64-v${PV}"

do_configure () {
	${S}/mingw-w64-headers/configure \
		--build=${BUILD} \
   		--host=${TARGET} \
		--prefix=${SYSROOT_DIR}/${TARGET}
}

do_compile () {
  :
}

do_install () {
 	runmake install
	cd ${SYSROOT_DIR}
	ln -s ${TARGET} mingw
    mkdir -p ${SYSROOT_DIR}/${TARGET}/lib
	ln -s ${SYSROOT_DIR}/${TARGET}/lib ${SYSROOT_DIR}/${TARGET}/lib64 
}
