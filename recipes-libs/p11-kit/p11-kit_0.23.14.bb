DESCRIPTION = "Libtasn1 is the ASN.1 library used by GnuTLS, p11-kit and some other packages."
DEPENDS = "gettext libtasn1 libffi"
PR = "r1"
SRC_URI = "\
    https://github.com/p11-glue/p11-kit/releases/download/${PV}/p11-kit-${PV}.tar.gz\
     "
SRC_URI[md5sum] = "85e1cdb5fce0087711ba2521975f0420"
LICENSE = "LGPLv2.1"
APP_DIR = "mingw"
do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} 
       # CFLAGS=--sysroot=${SYSROOT_DIR} 
}