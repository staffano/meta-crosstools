DESCRIPTION = "Pkg-config-wrapper makes sure there is a pkg-config configured for the target environemnt"
PR = "r1"
LICENSE = "GPLv2"
DESCRIPTION = "Script"

PV = "1.0.0"

addtask patch before do_build
do_build() {
     mkdir -p ${BUILD_TOOL_DIR}/bin/
     cat > ${BUILD_TOOL_DIR}/bin/${TARGET}-pkg-config <<- "EOF"
#!/bin/sh

export PKG_CONFIG_DIR=
export PKG_CONFIG_LIBDIR=${INSTALL_DIR}/lib/pkgconfig:${INSTALL_DIR}/share/pkgconfig
export PKG_CONFIG_SYSROOT_DIR=${INSTALL_DIR}

exec pkg-config "$@"
EOF
chmod +x ${BUILD_TOOL_DIR}/bin/${TARGET}-pkg-config
}

deltask do_configure 
deltask do_compile 
deltask do_install 
deltask do_populate_sysroot
deltask do_populate_lic 
deltask do_rm_work
deltask do_fetch
