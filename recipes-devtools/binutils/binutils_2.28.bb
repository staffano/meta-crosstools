SUMMARY = "GNU cc and gcc C compilers"
HOMEPAGE = "http://www.gnu.org/software/gcc/"
SECTION = "devel"
LICENSE = "GPL"

SRC_URI = "\
     http://mirror.switch.ch/ftp/mirror/gnu/binutils/binutils-${PV}.tar.bz2 \
     "
SRC_URI[md5sum] = "9e8340c96626b469a603c15c9d843727"
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
