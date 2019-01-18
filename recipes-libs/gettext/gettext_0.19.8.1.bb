DESCRIPTION = "Provides a way to load and enumerate PKCS#11 modules"
DEPENDS = "virtual/final-gcc libiconv"
PR = "r2"
SRC_URI = "\
  https://ftp.gnu.org/pub/gnu/gettext/gettext-${PV}.tar.xz \
     "
SRC_URI[md5sum] = "df3f5690eaa30fd228537b00cb7b7590"
LICENSE = "LGPLv2.1"

do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} \ 
       CFLAGS="-O2 --sysroot=${SYSROOT_DIR}" \
       CXXFLAGS="-O2 --sysroot=${SYSROOT_DIR}"
# The -O2 optimization flags when compiling for mingw32 due to a bug:
# http://savannah.gnu.org/bugs/?36443
}
