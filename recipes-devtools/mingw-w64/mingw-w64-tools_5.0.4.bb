require mingw-w64-common.inc

DESCRIPTION = "MINGW-W64 tools"
DEPENDS = "gcc-cross-final"

do_configure () {
  ${S}/configure \
    --prefix=${SYSROOT_DIR}/${TARGET} \
    --build=${BUILD} \
    --host=${TARGET} \
    --without-headers  \
    --without-crt \
    --disable-lib32 \
    --with-tools=all
}


