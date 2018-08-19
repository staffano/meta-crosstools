# meta-crosstools

meta-crosstools is a repository of bitbake recipes that can be used to build both native and cross compilers for Ada, C and C++.
Configuration files exists for the following host/target combos.


| Host                 | Target                        |
|----------------------|-------------------------------|
|x86_64-pc-linux-gnu   | x86_64-pc-linux-gnu           |
|x86_64-pc-linux-gnu   | mipsel-unknown-linux-gnu      |
|x86_64-pc-linux-gnu   | arm-unknown-linux-gnueabihf   |
|x86_64-pc-linux-gnu   | cris-elf                      |
|x86_64-pc-linux-gnu   | x86_64-w64-mingw32            |
|x86_64-w64-mingw32    | x86_64-w64-mingw32            |
|x86_64-w64-mingw32    | mipsel-unknown-linux-gnu      |
|x86_64-w64-mingw32    | arm-unknown-linux-gnueabihf   |

It is however easy to add support for additional toolchains.

The major usecase for this component is to be the backbone builder for [tcb](https://github.com/staffano/tcb), which is a go/docker tool that makes it possible to build these toolchains on platforms that support go/docker.

## Dependencies between toolchains

There is a dependence to having certain toolchains installed before a toolchains can be built. This dependency is expressed inside the .conf files using the following syntax:
```
# /// DEPENDENCIES=native,native-mingw,native-mipsel
``` 
The conf files take care of adding the path to the required toolchains relative to the `${IMAGES}` variable. If you install the required toolchains elsewhere, you need to add the path to these toolchains yourself.

## Building

Say you want to build a toolchain for crosscompiling to mingw, then you first need to build the native toolchains and then the native-mingw toolchain.

```shell
>bitbake -R conf/toolchains/native.conf image
>bitbake -R conf/toolchains/native-mingw.conf image
```

## Installation

By running the `image` target, the toolchain is installed in the `tmp/IMAGES` directory.

## Versions 

### recipes-devtools

|  Package  |  Version |
|-----------|----------|
| binutils  | 2.31.1 |
| eglibc | 2.15 / 2.19 |
| expat | 2.1.0
| gcc | 8.2.0 |
| gdb | 8.1.1 |
| glibc | 2.28 |
| gmp | 6.1.2 |
| isl | 0.18 |
| linux-headers  | 3.2 / 4.4 / rpi3_4.9 |
| mingw-w64 | git (master) |
| mpc | 1.0.3 |
| mpfr | 3.1.6 |
| ncurses | 6.0 |
| newlib | 2.5.0 |

### recipes-gnat

|  Package  |  Version |
|-----------|----------|
| gprbuild | git (SRCREV=23f880d69854e4900248d923c9790057da44d492) |
| xmlada | git (master) |

## License

See [License](LICENSE)

## TODO:

### Updates:
gdb: 8.1.1


