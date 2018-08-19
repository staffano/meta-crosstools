DESCRIPTION = "gprbuild"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+git${SRCPV}"

SRCREV = "23f880d69854e4900248d923c9790057da44d492"
SRC_URI = " \
    git://github.com/AdaCore/gprbuild.git;protocol=https \
   	file://001-x86_64-w64-mingw32.patch \
"
S = "${WORKDIR}/git"

# addtask patch before do_configure
do_patch () {
  cd ${S}
  patch -p1 < ${WORKDIR}/001-x86_64-w64-mingw32.patch
}