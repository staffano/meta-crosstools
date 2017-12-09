DESCRIPTION = "isl for target"
DEPENDS = "gmp"
PR = "r1"
SRC_URI = "\
      http://isl.gforge.inria.fr/isl-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "5337cd4c29101fe8e8e1b0f5948f91e2"
LICENSE = "GPLv3"

do_configure() {
    ${S}/configure  \
      --prefix=${INSTALL_DIR} \
      --build=${BUILD} \
      --host=${HOST} \
      --disable-shared \
      --enable-static \
      --with-gmp-prefix="${INSTALL_DIR}" \
      --with-piplib=no \
      --with-clang=no
}