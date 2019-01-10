DESCRIPTION = "Pkg-config-lite is a helper tool used when compiling applications and libraries."
PR = "r1"
SRC_URI = "\
     http://sourceforge.net/projects/pkgconfiglite/files/${PV}/pkg-config-lite-${PV}.tar.gz \
     "
SRC_URI[md5sum] = "61f05feb6bab0a6bbfab4b6e3b2f44b6"
LICENSE = "GPLv2"
do_configure() {
    ${S}/configure  \
      --prefix=${BUILD_TOOL_DIR} \
      --build=${BUILD} \
      --host=${BUILD} \
      --target=${TARGET} 
}

