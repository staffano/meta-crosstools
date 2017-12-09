inherit gnusources

DESCRIPTION = "gdb for target"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gdb/gdb-${PV}.tar.xz  \
    "
SRC_URI[md5sum] = "5aa71522e488e358243917967db87476"
LICENSE = "GPLv3"

S = "${WORKDIR}/gdb-${PV}"
B = "${WORKDIR}/gdbserver.${BUILD}.${TARGET}"

do_configure () {
  ${S}/gdb/gdbserver/configure \
  --prefix=${TARGET_INSTALL_DIR} \
  --host=${TARGET}
}
