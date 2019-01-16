DESCRIPTION = "gmp for sysroot"
PR = "r1"
SRC_URI = "\
     ftp://ftp.gmplib.org/pub/gmp-${PV}/gmp-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "f58fa8001d60c4c77595fbbb62b63c1d"
LICENSE = "GPLv3"
S = "${WORKDIR}/gmp-${PV}"
do_configure () {
  ${S}/configure  \
    --build=${BUILD} \
    --host=${TARGET} \
    --target=${TARGET} \
    --prefix=${SYSROOT_DIR} \
    --enable-fat \
    --enable-cxx \
    CPPFLAGS='-fexceptions'
}
