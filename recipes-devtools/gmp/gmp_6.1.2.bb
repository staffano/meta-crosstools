DESCRIPTION = "gmp for target"
PR = "r1"
SRC_URI = "\
     ftp://ftp.gmplib.org/pub/gmp-${PV}/gmp-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "f58fa8001d60c4c77595fbbb62b63c1d"
LICENSE = "GPLv3"

do_configure () {
  ${S}/configure  \
    --build=${BUILD} \
    --host=${HOST} \
    --prefix=${INSTALL_DIR} \
    --enable-fat \
    --disable-shared \
    --enable-static \
    --enable-cxx \
    CPPFLAGS='-fexceptions'
}
