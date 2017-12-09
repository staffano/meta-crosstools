inherit gnusources

DESCRIPTION = "gdb for target"

PR = "r1"
SRC_URI = "\
    ${GNU_SOURCE}/gdb/gdb-${PV}.tar.xz  \
    "
SRC_URI[md5sum] = "5aa71522e488e358243917967db87476"
LICENSE = "GPLv3"

S = "${WORKDIR}/gdb-${PV}"
B = "${WORKDIR}/gdb.${BUILD}.${TARGET}"

do_configure () {
  ${S}/configure \
    --prefix=${TARGET_INSTALL_DIR} \
    --target=${TARGET} \
    --with-expat \
    --disable-gdbtk --disable-tui --disable-x --disable-werror \
                    --with-curses --disable-multilib --disable-sim \
                    --without-lzma --without-guile \
                    --disable-rpath \
                    --disable-gas --disable-binutils \
                    --disable-ld --disable-gold \
                    --disable-gprof
}
