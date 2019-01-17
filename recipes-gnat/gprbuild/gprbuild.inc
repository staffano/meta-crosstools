DESCRIPTION = "gprbuild"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.24+git${SRCPV}"

SRCREV = "23f880d69854e4900248d923c9790057da44d492"
SRC_URI = " \
    git://github.com/AdaCore/gprbuild.git;protocol=https \
      file://staffano_compilers.xml \
    	file://staffano_configurations.xml \
      file://001-windres-prefix.patch \
"
S = "${WORKDIR}/git"

# addtask patch after do_fetch
do_patch () {
  cd ${S}
  cp ${WORKDIR}/staffano_compilers.xml ${S}/share/gprconfig/
  cp ${WORKDIR}/staffano_configurations.xml ${S}/share/gprconfig/
  
  patch -p1 < ${WORKDIR}/001-windres-prefix.patch

}