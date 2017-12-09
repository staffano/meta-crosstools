DESCRIPTION = "gprbuild"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+git${SRCPV}"

#SRCREV = "${AUTOREV}"
SRC_URI = " \
    git://github.com/AdaCore/gprbuild.git;protocol=https;rev=master \
   	file://001-mingw-w64.patch \
"
S = "${WORKDIR}/git"

addtask patch before do_configure
do_patch () {
  cd ${S}
  patch -p1 < ${WORKDIR}/001-mingw-w64.patch
}