inherit gnusources

DESCRIPTION = "eGLIBC Headers and startup files."

DEPENDS = "gcc-cross-initial virtual/linux-headers"

# Reuse pokys namein scheme
PROVIDES += "libc-for-gcc"

PR = "r1"
SRC_URI = "\
    http://downloads.yoctoproject.org/releases/eglibc/eglibc-${PV}-svnr25243.tar.bz2 \
    "
SRC_URI[md5sum] = "197836c2ba42fb146e971222647198dd"
LICENSE = "GPLv3"

S = "${WORKDIR}/eglibc-${PV}"
B = "${WORKDIR}/eglibc.${BUILD}.${TARGET}"

do_configure () {

  # We want the headers in ${TARGET_SYSROOT}/usr/include
  # alongside the linux kernal headers
  ${S}/libc/configure \
    --prefix=/usr \
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
  runmake install-bootstrap-headers=yes install-headers install_root=${SYSROOT_DIR}
  mkdir -p ${SYSROOT_DIR}/usr/lib/
  runmake csu/subdir_lib
  install -m 644 csu/crt[1in].o ${SYSROOT_DIR}/usr/lib/
  cd csu
  ${TARGET}-gcc -nostdlib -nostartfiles -shared -x c /dev/null -o ${SYSROOT_DIR}/usr/lib/libc.so

}
