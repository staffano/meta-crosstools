DESCRIPTION = "Create a portable image of the toolchain"

DEPENDS = "${IMAGE_FEATURES}"

PR = "r1"

do_build () {
    mkdir -p ${IMAGE_DIR}
    cd ${IMAGE_DIR}
    cp -a ${INSTALL_DIR} ${HOST}.${TARGET}
    if [ -d "${SYSROOT_DIR}" ]; then
            if [ -d "${SYSROOT_DIR}/${TARGET}" ]; then
                # SYSROOT/$TARGET/$TARGET -> INSTALL/$TARGET.$TARGET/$TARGET
                cp -a ${SYSROOT_DIR}/${TARGET} ${HOST}.${TARGET}/${TARGET}
            elif  [ -d "${SYSROOT_DIR}" ]; then
                 cp -a ${SYSROOT_DIR} ${HOST}.${TARGET}/${TARGET}
            else
                echo "Improper SYSROOT"
            fi
    fi
    tar zcf ${HOST}.${TARGET}.tar.gz ${HOST}.${TARGET}
    rm -rf ${HOST}.${TARGET}
}

# Used to print the location of the generated image
# bitbake -c do_ls image
do_copy_image() {
    rm -rf ${RESULT_DIR}/${HOST}.${TARGET}.tar.gz
    cp ${IMAGE_DIR}/${HOST}.${TARGET}.tar.gz ${RESULT_DIR}/
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
