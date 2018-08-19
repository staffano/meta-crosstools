require gcc-common_${PV}.inc

DEPENDS = "gmp mpfr isl mpc binutils"
PROVIDES += "virtual/final-gcc"

do_configure () {
  ${S}/configure \
  	--build=${BUILD} \
  	--host=${HOST} \
  	--target=${TARGET} \
  	--disable-nls \
  	--disable-multilib \
  	--prefix=${INSTALL_DIR} \
  	--with-mpc=${INSTALL_DIR} \
  	--with-mpfr=${INSTALL_DIR} \
  	--with-gmp=${INSTALL_DIR}  \
  	--with-isl=${INSTALL_DIR} \
  	--enable-languages=c,c++,ada \
  	--enable-threads=posix \
  	--enable-fully-dynamic-string
}
