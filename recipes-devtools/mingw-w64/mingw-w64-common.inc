LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+git${SRCPV}"
SRCREV = "2d4e517ad0c7a9f0bd7001c42e6c131b977c15d9"
SRC_URI = " \
    git://git.code.sf.net/p/mingw-w64/mingw-w64;protocol=https \
"
S = "${WORKDIR}/git"

LICENSE = "GPLv3"
