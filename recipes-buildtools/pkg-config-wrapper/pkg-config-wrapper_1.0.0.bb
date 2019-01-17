DESCRIPTION = "Pkg-config-wrapper makes sure there is a pkg-config configured for the target environemnt"
PR = "r1"
LICENSE = "GPLv2"
DESCRIPTION = "Script"

PV = "1.0.0"
APP_DIR = "mingw"
do_install() {
     mkdir -p ${BUILD_TOOL_DIR}/bin/
     cat > ${BUILD_TOOL_DIR}/bin/${TARGET}-pkg-config <<- "EOF"
#!/bin/sh

export PKG_CONFIG_DIR=
export PKG_CONFIG_LIBDIR=${SYSROOT_DIR}/${APP_DIR}/lib/pkgconfig:${SYSROOT_DIR}/${APP_DIR}/share/pkgconfig
# export PKG_CONFIG_SYSROOT_DIR=${SYSROOT_DIR}

exec pkg-config "$@"
EOF
chmod +x ${BUILD_TOOL_DIR}/bin/${TARGET}-pkg-config
}

deltask do_configure 
deltask do_compile 
deltask do_populate_sysroot
deltask do_populate_lic 
deltask do_rm_work
deltask do_fetch
