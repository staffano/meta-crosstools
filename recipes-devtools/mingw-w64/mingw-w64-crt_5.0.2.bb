inherit gnusources

DESCRIPTION = "MINGW-W64 CRT"

DEPENDS = "gcc-cross-second"

PR = "r1"
SRC_URI = "\
   http://downloads.sourceforge.net/project/mingw-w64/mingw-w64/mingw-w64-release/mingw-w64-v${PV}.tar.bz2 \
    "
SRC_URI[md5sum] = "80d6884c9da234e73054347f44158b8a"

LICENSE = "GPLv3"

S = "${WORKDIR}/mingw-w64-v${PV}"

do_configure () {
  echo $PATH
  # We compensate at installation time by specifying install_root

  ${S}/configure \
    --prefix=${SYSROOT_DIR}/${TARGET} \
    --build=${BUILD} \
    --host=${TARGET} \
    --with-sysroot=${SYSROOT_DIR}/${TARGET} 
}


