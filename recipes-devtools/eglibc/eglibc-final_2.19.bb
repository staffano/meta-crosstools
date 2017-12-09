inherit gnusources

DESCRIPTION = "GLIBC Headers and startup files."

DEPENDS = "gcc-cross-second"

PROVIDES +="libc"

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

  # "The Filesystem Hierarchy Standard for GNU/Linux systems expects some
  # components of the GNU C Library installation to be in /lib and some
  # in /usr/lib. This is handled automatically if you configure the
  # GNU C Library with ‘--prefix=/usr’."
  # From https://www.gnu.org/software/libc/manual/html_node/Linux.html#Linux

  # We compensate at installation time by specifying install_root

  ${S}/libc/configure \
    --prefix=/usr \
    --build=${BUILD} \
    --host=${TARGET} \
    --with-headers=${SYSROOT_DIR}/usr/include \
    ${ADDITIONAL_GLIBC_CONF} \
    --disable-profile

}

do_install () {
  runmake install install_root=${SYSROOT_DIR}
}
