inherit gnusources

DESCRIPTION = "gcc for native"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.xz \
    "
SRC_URI[md5sum] = "ff370482573133a7fcdd96cd2f552292"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
