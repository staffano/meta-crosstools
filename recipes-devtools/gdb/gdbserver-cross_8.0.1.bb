inherit gnusources

DESCRIPTION = "gdb for target"
DEPENDS = "virtual/final-gcc"
PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gdb/gdb-${PV}.tar.xz  \
    "
SRC_URI[md5sum] = "48cac527e6f3018b865ece021e9723ac"
LICENSE = "GPLv3"

S = "${WORKDIR}/gdb-${PV}"
B = "${WORKDIR}/gdbserver.${BUILD}.${TARGET}"

do_configure () {
  ${S}/gdb/gdbserver/configure \
      --prefix=${INSTALL_DIR}/${TARGET} \
      --build=${BUILD} \
      --host=${TARGET}
}
