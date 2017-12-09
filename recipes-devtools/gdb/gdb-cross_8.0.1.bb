inherit gnusources

DESCRIPTION = "gdb for target"

DEPENDS = "virtual/final-gcc expat"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gdb/gdb-${PV}.tar.xz  \
    "
SRC_URI[md5sum] = "48cac527e6f3018b865ece021e9723ac"
LICENSE = "GPLv3"

S = "${WORKDIR}/gdb-${PV}"
B = "${WORKDIR}/gdb.${BUILD}.${TARGET}"

do_configure () {
  ${S}/configure \
    --prefix=${INSTALL_DIR} \
    --build=${BUILD} \
    --host=${HOST} \
    --target=${TARGET} \
    --with-expat
}
