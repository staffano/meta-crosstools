DESCRIPTION = "Libtasn1 is the ASN.1 library used by GnuTLS, p11-kit and some other packages."

PR = "r1"
SRC_URI = "\
      https://ftp.gnu.org/gnu/libtasn1/libtasn1-${PV}.tar.gz \
     "
SRC_URI[md5sum] = "ce2ba4d3088119b48e7531a703669c52"
LICENSE = "LGPLv2.1"
APP_DIR = "mingw"
do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} 
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}