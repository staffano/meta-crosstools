inherit gnusources

DESCRIPTION = "Linux kernel headers for raspberry Pi3"

DEPENDS = "gcc-cross-initial"
PROVIDES = "virtual/linux-headers"
PR = "r1"

SRC_URI = "\
      https://github.com/raspberrypi/linux/archive/rpi-${PV}.y.zip \
     "
SRC_URI[md5sum] = "6d2d8cb442501fffd73df02f668e1254"
LICENSE = "GPLv3"
S = "${WORKDIR}/linux-rpi-${PV}.y"

do_compile () {
:
}

do_install () {
  cd ${S}
  runmake INSTALL_HDR_PATH=${SYSROOT_DIR}/usr ARCH=${KERNEL_ARCH} headers_install
}
