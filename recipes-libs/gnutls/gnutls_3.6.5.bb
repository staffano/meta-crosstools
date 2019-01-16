DESCRIPTION = "The GnuTLS Transport Layer Security Library"
DEPENDS = "nettle gmp pkg-config-wrapper"
PR = "r1"
SRC_URI = "\
     http://ftp.heanet.ie/mirrors/ftp.gnupg.org/gcrypt/gnutls/v3.6/gnutls-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "3474849e1bbd4d16403b82ab2579000b"
LICENSE = "LGPLv2.1+"

do_configure () {
  ${S}/configure  \
    --prefix=${INSTALL_DIR} \
    --build=${BUILD} \
    --host=${TARGET} \
    --disable-shared
}
