DESCRIPTION = "mpfr for TARGET"
DEPENDS = "gmp"
PR = "r1"
SRC_URI = "\
     http://www.mpfr.org/mpfr-${PV}/mpfr-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "51bfdbf81553966c8d43808122cc81b3"
LICENSE = "GPLv3"
do_configure() {
    ${S}/configure  \
      --prefix=${INSTALL_DIR} \
      --build=${BUILD} \
      --host=${HOST} \
      --target=${TARGET} \
      --disable-shared \
      --enable-static \
      --with-gmp=${INSTALL_DIR}
}

