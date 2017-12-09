inherit gnusources

# cf. https://sourceforge.net/p/mingw-w64/wiki2/Cross%20Win32%20and%20Win64%20compiler/
DESCRIPTION = "MinGW-W64 version of winpthreads"

DEPENDS = "mingw-w64-winpthreads"

PROVIDES +="libc"

do_install() {
 :
}
