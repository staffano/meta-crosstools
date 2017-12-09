DESCRIPTION = "mpc for TARGET"
DEPENDS = "mpfr gmp"
PR = "r1"
SRC_URI = "\
     http://www.multiprecision.org/mpc/download/mpc-${PV}.tar.gz \
     "
SRC_URI[md5sum] = "d6a1d5f8ddea3abd2cc3e98f58352d26"
LICENSE = "GPLv3"

do_configure() {
    ${S}/configure  \
      --prefix=${INSTALL_DIR} \
      --build=${BUILD} \
      --host=${HOST} \
      --target=${TARGET} \
      --disable-shared \
      --enable-static \
      --with-gmp=${INSTALL_DIR} \
      --with-mpfr=${INSTALL_DIR}
}

