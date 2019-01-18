DESCRIPTION = "SQLite is a C-language library that implements a small, fast, self-contained, \
               high-reliability, full-featured, SQL database engine. "
DEPENDS = "virtual/final-gcc"
PR = "r1"
SRC_URI = "\
     https://sqlite.org/2018/sqlite-autoconf-3260000.tar.gz \
     "
SRC_URI[md5sum] = "ac2b3b8cd3a97600e36fb8e756e8dda1"
LICENSE = "Public Domain"

S = "${WORKDIR}/sqlite-autoconf-3260000"
do_configure() {
    ${S}/configure  \
      --prefix=${SYSROOT_DIR}/${APP_DIR} \
      --build=${BUILD} \
      --host=${TARGET} \
      CFLAGS=--sysroot=${SYSROOT_DIR}
}