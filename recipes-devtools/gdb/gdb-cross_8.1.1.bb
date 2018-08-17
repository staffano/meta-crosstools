inherit gnusources

DESCRIPTION = "gdb for target"

DEPENDS = "virtual/final-gcc expat"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gdb/gdb-${PV}.tar.xz  \
    "
SRC_URI[md5sum] = "372fa63a53adeee1bfbfd581d8da9e34"
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
