inherit gnusources

DESCRIPTION = "Linux kernel headers for raspberry Pi3"

DEPENDS = "gcc-cross-initial"
PROVIDES = "virtual/linux-headers"
PR = "r1"

SRC_URI = "\
      https://github.com/raspberrypi/linux/archive/rpi-${PV}.y-stable.zip \
     "
SRC_URI[md5sum] = "e3e40ef914cbc4f4a7659350091e2dd6"
LICENSE = "GPLv3"
S = "${WORKDIR}/linux-rpi-${PV}.y-stable"

do_compile () {
:
}

do_install () {
  cd ${S}
  runmake INSTALL_HDR_PATH=${SYSROOT_DIR}/usr ARCH=${KERNEL_ARCH} headers_install
}
