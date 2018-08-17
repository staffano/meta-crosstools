inherit gnusources

DESCRIPTION = "gcc for native"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.xz \
    "
SRC_URI[md5sum] = "4ab282f414676496483b3e1793d07862"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
