require newlib.inc

DEPENDS = "gcc-cross-initial"

PROVIDES += "libc-for-gcc libc"


do_configure () {
  ${S}/configure \
    --prefix=${INSTALL_DIR} \
    --target=${TARGET} 

}

do_compile () {
  runmake ${MAKE_JX}
}

do_install () {
  runmake install
}
