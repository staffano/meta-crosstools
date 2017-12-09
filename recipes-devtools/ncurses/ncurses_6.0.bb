inherit gnusources
DESCRIPTION = "binutils for native"

PR = "r1"

SRC_URI = "\
     ${GNU_SOURCE}/ncurses/ncurses-${PV}.tar.gz  \
     "
SRC_URI[md5sum] = "ee13d052e1ead260d7c28071f46eefb1"
LICENSE = "GPLv3"

S = "${WORKDIR}/ncurses-${PV}"
B = "${WORKDIR}/ncurses.${BUILD}.${TARGET}"

do_configure () {

  ${S}/configure \
  --without-tests \
  --host=${_host} \
  --prefix=${_prefix} \
  --with-build-gcc=gcc || exit 1

make ${make_opts} || exit 1
make install || exit 1

do_build() {
  echo "first: some shell script running as build"
}
