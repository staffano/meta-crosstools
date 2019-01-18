DESCRIPTION = "Create a portable image of the toolchain and some basic libs"

DEPENDS = "${TOOLCHAIN_IMAGE_FEATURES} \
    pkg-config-wrapper libffi \
    libiconv libtasn1 nettle p11-kit gmp-sysroot gnutls \
    sqlite"

PR = "r2"

IMAGE_NAME = "${PN}-${HOST}.${TARGET}"

do_build () {
    mkdir -p ${IMAGE_DIR}
    cd ${IMAGE_DIR}
    cp -a ${INSTALL_DIR} ${HOST}.${TARGET}
    tar hzcf ${IMAGE_NAME}.tar.gz ${HOST}.${TARGET}
    rm -rf ${HOST}.${TARGET}
}

# Used to print the location of the generated image
# bitbake -c do_ls image
do_copy_image() {
    rm -rf ${RESULT_DIR}/${IMAGE_NAME}.tar.gz
    cp ${IMAGE_DIR}/${IMAGE_NAME}.tar.gz ${RESULT_DIR}/
}
do_configure() {
    :
}
do_compile() {
    :
}
do_install() {
    :
}
addtask do_copy_image
deltask do_populate_sysroot
deltask do_populate_lic 
deltask do_rm_work
