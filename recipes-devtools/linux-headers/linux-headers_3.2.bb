inherit gnusources

DESCRIPTION = "Linux kernel headers"

DEPENDS = "gcc-cross-initial"
PROVIDES = "virtual/linux-headers"

PR = "r1"

SRC_URI = "\
      ${KERNEL_SOURCES}/v3.x/linux-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "364066fa18767ec0ae5f4e4abcf9dc51"

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
