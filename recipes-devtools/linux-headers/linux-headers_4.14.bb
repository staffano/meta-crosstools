inherit gnusources

DESCRIPTION = "Linux kernelheaders"

DEPENDS = "gcc-cross-initial"
PROVIDES = "virtual/linux-headers"

PR = "r1"

SRC_URI = "\
      ${KERNEL_SOURCES}/v4.x/linux-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "9a78fa2eb6c68ca5a40ed5af08142599"
LICENSE = "GPLv3"
S = "${WORKDIR}/linux-${PV}"

do_compile () {
  echo "Nothing to be compiled"
}

do_install () {
  cd ${S}
	runmake INSTALL_HDR_PATH=${SYSROOT_DIR}/usr ARCH=${KERNEL_ARCH} headers_check
  runmake INSTALL_HDR_PATH=${SYSROOT_DIR}/usr ARCH=${KERNEL_ARCH} headers_install
}
