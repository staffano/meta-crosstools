SUMMARY = "GNU cc and gcc C compilers"
HOMEPAGE = "http://www.gnu.org/software/gcc/"
SECTION = "devel"
LICENSE = "GPL"

SRC_URI = "\
     http://ftp.acc.umu.se/mirror/gnu.org/gnu/binutils/binutils-${PV}.tar.bz2 \
     "
SRC_URI[md5sum] = "84edf97113788f106d6ee027f10b046a"
LICENSE = "GPLv3"

PR = "r1"

CONFIG_OPTS = " \
    --prefix=${INSTALL_DIR} \
    --program-prefix=${TARGET}- \
    --target=${TARGET} \
    --host=${HOST} \
    --build=${BUILD} \
    --with-sysroot=${SYSROOT_DIR} \
"

CONFIG_OPTS_remove_newlib = " --with-sysroot=${SYSROOT_DIR} "

do_configure ()  {
  ${S}/configure ${CONFIG_OPTS}
}
