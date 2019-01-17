DESCRIPTION = "Provides a way to load and enumerate PKCS#11 modules"
DEPENDS = "libiconv"
PR = "r1"
SRC_URI = "\
  https://ftp.gnu.org/pub/gnu/gettext/gettext-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "df3f5690eaa30fd228537b00cb7b7590"
LICENSE = "LGPLv2.1"
APP_DIR = "mingw"
do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} 
       CFLAGS=--sysroot=${SYSROOT_DIR} 
}