DESCRIPTION = "mpc for TARGET"
DEPENDS = "virtual/final-gcc gmp-sysroot"
PR = "r1"
SRC_URI = "\
      https://ftp.gnu.org/gnu/nettle/nettle-${PV}.tar.gz \
     "
SRC_URI[md5sum] = "9bdebb0e2f638d3b9d91f7fc264b70c1"
LICENSE = "LGPLv3+ | GPLv2+"

do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} \
      --enable-shared \
      --disable-static \
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}