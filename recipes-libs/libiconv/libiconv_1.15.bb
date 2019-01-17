DESCRIPTION = "Library for conversion of international text."
PR = "r1"
SRC_URI = "\
  https://ftp.gnu.org/pub/gnu/libiconv/libiconv-${PV}.tar.gz \
     "
SRC_URI[md5sum] = "ace8b5f2db42f7b3b3057585e80d9808"
LICENSE = "LGPLv2.1"
APP_DIR = "mingw"
do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} 
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}