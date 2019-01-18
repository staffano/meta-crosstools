DESCRIPTION = "AWS is a complete framework to develop Web based applications in Ada."
DEPENDS = "xmlada-cross gprbuild-cross gnutls"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING3;md5=4fbd65380cdd255951079008b364516c"

PV = "git${SRCPV}"

SRCREV = "81b2d66c3877c18408ae03aeba4949c63b4e9c29"
SRC_URI = " \
    gitsm://github.com/AdaCore/aws.git;protocol=https \
"
S = "${WORKDIR}/git"
## DOES NOT WORK on mingw. The wrong windres compiler is picked up.

# Build gpr build to execute on host.
do_configure() {
	cd ${S}
  	make prefix=${INSTALL_DIR} NETLIB=ipv4 SOCKET=gnutls ENABLE_SHARED=false TARGET=${TARGET} setup  
}

do_compile() {
	cd ${S}
	make ${MAKE_JX} build  
}

do_install() {
	cd ${S}
	make install 
}
