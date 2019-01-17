DESCRIPTION = "xmlada"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "gprbuild-bootstrap xmlada-source"

B = "${WORKDIR}/${BUILD}.${TARGET}"
S = "${TMPDIR}/work-shared/xmlada/git"
PATH_prepend = "${TMPDIR}/work-shared/gprbuild/bootstrap/bin:"

do_preconfigure() {
	cp -a ${S} ${B}
}

do_preconfigure[depends] = "xmlada-source:do_build"

addtask preconfigure before do_configure

do_configure () {    
    ${S}/configure \
	--srcdir=${S}
}

do_install () {
	  make all install IPREFIX=${INSTALL_DIR}
}
do_fetch() {
  :
}

deltask do_patch

