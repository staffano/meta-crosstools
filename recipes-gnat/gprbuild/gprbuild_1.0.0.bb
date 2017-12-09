require gprbuild.inc

DEPENDS = "xmlada"

B = "${WORKDIR}/${BUILD}.${TARGET}"
PATH_prepend = "${TMPDIR}/work-shared/gprbuild/bootstrap/bin:"

do_configure() {
 	make -f ${S}/Makefile prefix=${INSTALL_DIR} SOURCE_DIR=${S} TARGET=${TARGET} setup 
}

do_compile() {
	make -f ${S}/Makefile ${MAKE_JX} all  
	make -f ${S}/Makefile ${MAKE_JX} libgpr.build 
	make -f ${S}/Makefile ${MAKE_JX} libgpr.build.shared 
}

do_install() {
	make -f ${S}/Makefile install 
	make -f ${S}/Makefile libgpr.install 
	make -f ${S}/Makefile libgpr.install.shared	
}
