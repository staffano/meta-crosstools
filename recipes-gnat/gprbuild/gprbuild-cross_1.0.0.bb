require gprbuild.inc

DEPENDS = "xmlada-cross"

B = "${WORKDIR}/${BUILD}.${TARGET}"

# Build gpr build to execute on host.
do_configure() {
 	make -f ${S}/Makefile prefix=${INSTALL_DIR} SOURCE_DIR=${S} TARGET=${HOST} setup 
}

do_compile() {
	export GPR_PROJECT_PATH="${INSTALL_DIR}/${TARGET}/share/gpr"
	make -f ${S}/Makefile ${MAKE_JX} all  
	make -f ${S}/Makefile ${MAKE_JX} libgpr.build.static 
#	make -f ${S}/Makefile ${MAKE_JX} libgpr.build.shared 
}

do_install() {
	export GPR_PROJECT_PATH="${INSTALL_DIR}/${TARGET}/share/gpr"
	make -f ${S}/Makefile install 
	make -f ${S}/Makefile libgpr.install.static 
#	make -f ${S}/Makefile libgpr.install.shared	
}
