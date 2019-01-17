DESCRIPTION = "xmlada sources"

LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "1.0.0+git${SRCPV}"

B = "${WORKDIR}/${BUILD}.${TARGET}"
S = "${WORKDIR}/git"

#SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/AdaCore/xmlada.git;protocol=https;rev=master"

# Now move the source to shared work.

addtask patch before do_build
do_build() {
	mkdir -p ${TMPDIR}/work-shared/xmlada
	mv ${WORKDIR}/git ${TMPDIR}/work-shared/xmlada/git
}


deltask do_configure 
deltask do_compile 
deltask do_install 
deltask do_populate_sysroot
deltask do_populate_lic 
deltask do_rm_work
