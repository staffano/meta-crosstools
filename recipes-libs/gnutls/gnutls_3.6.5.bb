DESCRIPTION = "The GnuTLS Transport Layer Security Library"
DEPENDS = "nettle gmp-sysroot pkg-config-wrapper libtasn1 p11-kit"
PR = "r1"
SRC_URI = "\
     http://ftp.heanet.ie/mirrors/ftp.gnupg.org/gcrypt/gnutls/v3.6/gnutls-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "3474849e1bbd4d16403b82ab2579000b"
LICENSE = "LGPLv2.1+"
APP_DIR = "mingw"
do_configure () {
  ${S}/configure  \
    --prefix=${SYSROOT_DIR}/${APP_DIR} \
    --build=${BUILD} \
    --host=${TARGET} \
    --disable-shared \
    --with-included-unistring \
    --disable-doc \
    --disable-test \
    --enable-openssl-compatibility \
    --enable-ssl3-support \
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}
