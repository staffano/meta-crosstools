inherit gnusources

DESCRIPTION = "GLIBC Headers and startup files."

DEPENDS = "gcc-cross-second"

PROVIDES +="libc"
SRC_URI = " \
	file://eglibc-${PV}.tar.gz \
	file://000-fix-make-version.patch \
	"
# SRC_URI[md5sum] = "197836c2ba42fb146e971222647198dd"
LICENSE = "GPLv3"

S = "${WORKDIR}/eglibc-${PV}"
B = "${WORKDIR}/eglibc.${BUILD}.${TARGET}"

addtask patch before do_configure
do_patch () {
  cd ${S}
  patch -p1 < ${WORKDIR}/000-fix-make-version.patch

  cp -r ${S}/ports ${S}/libc

  # Disable linking to libgcc_eh
  cd libc
  cp -v Makeconfig Makeconfig.orig
  sed -e 's/-lgcc_eh//g' Makeconfig.orig > Makeconfig
}

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
    --with-headers=${TARGET_SYSROOT}/usr/include \
    ${ADDITIONAL_GLIBC_CONF} \
    --disable-profile

}

do_install () {
  runmake install install_root=${TARGET_SYSROOT}
}
