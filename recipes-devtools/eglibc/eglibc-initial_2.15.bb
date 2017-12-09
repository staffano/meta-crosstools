inherit gnusources

DESCRIPTION = "eGLIBC Headers and startup files."

DEPENDS = "gcc-cross-initial virtual/linux-headers"

# Reuse pokys namein scheme
PROVIDES += "libc-for-gcc"

#SRCREV="25241"
#PR_append = "+svnr${SRCREV}"
EGLIBC_BRANCH="eglibc-2_15"
PR = "r1"
#SRC_URI = "svn://svn.eglibc.org/svn/branches;module=${EGLIBC_BRANCH};protocol=svn"

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

  # For eglibc 2.15 we need to copy the ports directory
  # into the libc

  # We want the headers in ${TARGET_SYSROOT}/usr/include
  # alongside the linux kernal headers
  ${S}/libc/configure \
    --prefix=/usr \
    --build=${BUILD} \
    --host=${TARGET} \
    --with-headers=${TARGET_SYSROOT}/usr/include \
    ${ADDITIONAL_GLIBC_CONF} \
    --disable-multilib
}

do_compile () {
  :
}

do_install () {
  runmake install-bootstrap-headers=yes install-headers install_root=${TARGET_SYSROOT}
  mkdir -p ${TARGET_SYSROOT}/usr/lib/
  runmake csu/subdir_lib
  install -m 644 csu/crt[1in].o ${TARGET_SYSROOT}/usr/lib/
  cd csu
  ${TARGET}-gcc -nostdlib -nostartfiles -shared -x c /dev/null -o ${TARGET_SYSROOT}/usr/lib/libc.so

}
