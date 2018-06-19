inherit gnusources

DESCRIPTION = "gcc for native"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gcc/gcc-${PV}/gcc-${PV}.tar.xz \
    "
SRC_URI[md5sum] = "65f7c65818dc540b3437605026d329fc"
LICENSE = "GPLv3"

S = "${WORKDIR}/gcc-${PV}"
