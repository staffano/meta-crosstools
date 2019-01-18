The libraries here will be part of the sysroot of the target toolchain. The motivation 
behind creating these recipes was to build a shared library of gnutls on windows.

So in order to use these libraries in the target system, the PATH needs to be set to include 
`<toolchain_dir>/<target>/sys-root/<app_dir>/bin`.

The app-dir is either `mingw` or `usr`, depending on the target.
