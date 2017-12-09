
B = "${WORKDIR}/${BUILD}.${TARGET}"

do_configure() {
}

do_compile() {
:
}

do_install() {
gprconfig --target=${TARGET} --config=Ada --config=C --batch
}
