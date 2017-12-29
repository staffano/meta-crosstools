
B = "${WORKDIR}/${BUILD}.${TARGET}"

do_configure() {
}

do_compile() {
:
}

do_install() {
gprconfig --target=${HOST} --config=Ada --config=C --batch
}
