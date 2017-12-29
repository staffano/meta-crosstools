DESCRIPTION = "xmlada"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "xmlada-source gprbuild-cross-bootstrap virtual/final-gcc"

B = "${WORKDIR}/${BUILD}.${TARGET}"
S = "${TMPDIR}/work-shared/xmlada/git"

do_preconfigure() {
	cp -a ${S} ${B}
}
do_preconfigure[depends] = "xmlada-source:do_build"

addtask preconfigure before do_configure

do_configure () {    
    ${S}/configure \
	--prefix=${INSTALL_DIR} \
	--srcdir=${S} \
	--target=${HOST}
}

do_fetch() {
  :
}

deltask do_patch

