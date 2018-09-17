DESCRIPTION = "gprbuild"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.24+git${SRCPV}"

SRCREV = "23f880d69854e4900248d923c9790057da44d492"
SRC_URI = " \
    git://github.com/AdaCore/gprbuild.git;protocol=https \
      file://staffano_cris.xml \
    	file://staffano_mipsel.xml \
    	file://staffano_rpi3.xml \
    	file://staffano_mingw.xml \
"
S = "${WORKDIR}/git"

# addtask patch before do_configure
do_patch () {
  cd ${S}
  cp ${WORKDIR}/staffano_cris.xml ${S}/share/gprconfig/
  cp ${WORKDIR}/staffano_mipsel.xml ${S}/share/gprconfig/
  cp ${WORKDIR}/staffano_rpi3.xml ${S}/share/gprconfig/
  cp ${WORKDIR}/staffano_mingw.xml ${S}/share/gprconfig/
}