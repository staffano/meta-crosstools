require gprbuild.inc

DEPENDS = "xmlada-cross"

B = "${WORKDIR}/${BUILD}.${TARGET}"

# Since gprbuild is not with prefix, we need to make sure the native version
# is first in the PATH

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
