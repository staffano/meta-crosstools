
DESCRIPTION = "Expat"

DEPENDS = ""

PR = "r1"
SRC_URI = "\
    http://downloads.sourceforge.net/expat/expat-${PV}.tar.gz \
    "
SRC_URI[md5sum] = "dd7dab7a5fea97d2a6a43f511449b7cd"
LICENSE = "GPLv3"

S = "${WORKDIR}/expat-${PV}"
B = "${WORKDIR}/expat.${BUILD}.${HOST}"

do_configure() {
    ${S}/configure  \
      --build=${BUILD} \
      --host=${HOST} \
      --prefix=${INSTALL_DIR}
}
