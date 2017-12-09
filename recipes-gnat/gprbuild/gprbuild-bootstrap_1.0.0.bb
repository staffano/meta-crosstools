require gprbuild.inc

DEPENDS = "xmlada-source virtual/final-gcc"

B = "${WORKDIR}/${BUILD}.${TARGET}"

XMLSOURCE = "${TMPDIR}/work-shared/xmlada/git"
GPRBUILD_BOOTSTRAP = "${TMPDIR}/work-shared/gprbuild/bootstrap"

do_configure() {
  :
}

do_install() {
  :
}
do_compile () {
	${S}/bootstrap.sh \
	--with-xmlada=${XMLSOURCE} \
	--prefix=${GPRBUILD_BOOTSTRAP} \
	--srcdir=${S}
}
do_compile[depends]="xmlada-source:do_build"

