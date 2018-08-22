inherit gnusources

DESCRIPTION = "GLIBC Headers and startup files."

DEPENDS = "gcc-cross-initial virtual/linux-headers"

# Reuse pokys namein scheme
PROVIDES += "libc-for-gcc"

PR = "r1"
SRC_URI = "\
    http://ftpmirror.gnu.org/glibc/glibc-${PV}.tar.xz \
    "
SRC_URI[md5sum] = "97dc5517f92016f3d70d83e3162ad318"
LICENSE = "GPLv3"

S = "${WORKDIR}/glibc-${PV}"
B = "${WORKDIR}/glibc.${BUILD}.${TARGET}"

do_configure () {

  # We want the headers in ${TARGET_SYSROOT}/usr/include
  # alongside the linux kernal headers

  ${S}/configure \
    --prefix=${SYSROOT_DIR}/usr \
    --build=${BUILD} \
    --host=${TARGET} \
    --with-headers=${SYSROOT_DIR}/usr/include \
    ${ADDITIONAL_GLIBC_CONF} \
    --disable-multilib
}

do_compile () {
  :
}

do_install () {
  runmake install-bootstrap-headers=yes install-headers

	runmake csu/subdir_lib
	mkdir -p ${SYSROOT_DIR}/lib/
	install -m 644 csu/crt[1in].o ${SYSROOT_DIR}/lib/

	# Two headers -- stubs.h and features.h -- aren't installed by install-headers,
	# so do them by hand.  We can tolerate an empty stubs.h for the moment.
	# See e.g. http://gcc.gnu.org/ml/gcc/2002-01/msg00900.html
	mkdir -p ${SYSROOT_DIR}/usr/include/gnu/
	touch ${SYSROOT_DIR}/usr/include/gnu/stubs.h
	cp ${S}/include/features.h ${SYSROOT_DIR}/usr/include/features.h

  # MAYBE: $_target-gcc -nostdlib -nostartfiles -shared -x c /dev/null -o $_sysroot/usr/lib/libc.so
}
