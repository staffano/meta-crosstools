require mingw-w64-common.inc

DESCRIPTION = "MINGW-W64 CRT"

DEPENDS = "gcc-cross-second"
PROVIDES += "libc"

do_configure () {
  ${S}/configure \
    --prefix=${SYSROOT_DIR}/${TARGET} \
    --build=${BUILD} \
    --host=${TARGET} \
    --without-headers  \
    --disable-lib32

}


