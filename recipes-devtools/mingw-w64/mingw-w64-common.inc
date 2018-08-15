LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+git${SRCPV}"

SRC_URI = " \
    git://git.code.sf.net/p/mingw-w64/mingw-w64;protocol=https;rev=master \
"
S = "${WORKDIR}/git"

LICENSE = "GPLv3"
