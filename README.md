# meta-crosstools

meta-crosstools is a repository of bitbake recipes that can be used to build both native and cross compilers for Ada, C and C++.
Configuration files exists for the following host/target combos. Native in this case means x86_64-pc-linux-gnu.

| Name          | Host                 | Target                        | Status | Libc          | Kernel| Features |
|---------------|----------------------|-------------------------------|--------|---------------|-------|-------------------|
| native        |x86_64-pc-linux-gnu   | x86_64-pc-linux-gnu           | Ok     | glibc-2.28    | 4.14  | gprbuild, c, c++, ada, gdb|
| native-mipsel |x86_64-pc-linux-gnu   | mipsel-unknown-linux-gnu      | Ok     | eglibc-2.19   | 4.9   | c, c++, ada, gdb|
| native-rpi3   |x86_64-pc-linux-gnu   | arm-unknown-linux-gnueabihf   | Ok     | glibc-2.24    | 4.9   | c, c++, ada, gdb|
| native-cris   |x86_64-pc-linux-gnu   | cris-elf                      | Ok     | newlib-2.5.0  | 4.14  | c, c++, ada, gcc, gdb|
| native-mingw  |x86_64-pc-linux-gnu   | x86_64-w64-mingw32            | Ok     | mingw-w64-git | N/A   | c, c++, ada, gdb|
| mingw-mingw   |x86_64-w64-mingw32    | x86_64-w64-mingw32            | Ok     | mingw-w64-git | N/A   | gprbuild, c, c++, ada, gdb|
| mingw-mipsel  |x86_64-w64-mingw32    | mipsel-unknown-linux-gnu      | Ok     | eglibc-2.19   | 4.9   | c, c++, ada, gdb|
| mingw-rpi3    |x86_64-w64-mingw32    | arm-unknown-linux-gnueabihf   | Ok     | glibc-2.24    | 4.9   | c, c++, ada, gdb|

It is however easy to add support for additional toolchains.

The major usecase for this component is to be the backbone builder for [tcb](https://github.com/staffano/tcb), which is a go/docker tool that makes it possible to build these toolchains on platforms that support go/docker.

## Quick-Start

### Debian/Ubuntu

Assuming you come frome a fresh and recent debian-based install, you can do the following to get up and running.

```bash
>sudo apt install -y build-essential gnat git locales python3 wget m4 gawk unzip nano texinfo rsync
>mkdir $HOME/toolchains
>cd toolchains
>git clone https://github.com/openembedded/bitbake.git
>git clone https://github.com/staffano/meta-crosstools.git 
>export PATH=$HOME/bitbake/bin:$PATH
>export PYTHONPATH=$HOME/bitbake/bin:$PYTHONPATH
>mkdir -p $HOME/toolchains/build/conf
>printf "BBPATH = \"${TOPDIR}\"\nBBFILES ?= \"\"\nBBLAYERS ?= \"${HOME}/toolchains/meta-crosstools\"\n" >> ${HOME}/toolchains/build/conf/bblayers.conf
>printf "BB_NUMBER_THREADS = \"4\"\nMAKE_JX := \"-j6\"\n" >> ${HOME}/toolchains/build/conf/local.conf
>cd build
>bitbake -R ../meta-crosstools/conf/toolchains/native-mipsel.conf image
...

```

## Dependencies between toolchains

There is a dependence to having certain toolchains installed before a toolchains can be built. This dependency is expressed inside the .conf files using the following syntax:

```none
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

## Docker support

By running inside a docker container, we can use this tool on Windows too. There are a few powershell scripts in the `scripts` folder to make this task easier.

If docker for windows is set up properly, with a good number of cpus and massive amount of disk space allocated for it, then building on windows is as easy as building on windows. It's even faster and easier to erase all the files!

The following command:

```powershell
>scripts\Build-Recipy.ps1 image mingw-mingw
```

will build the following images `x86_64-pc-linux-gnu.x86_64-pc-linux-gnu`, `x86_64-pc-linux-gnu.x86_64-w64-mingw32` and `x86_64-w64-mingw32.x86_64-w64-mingw32` and store them inside the `bb-tmp` docker volume.
In order to fetch an image from the `bb-tmp` volume, the `scripts/Get-Image.ps1` script exists.

 ```powershell
 >scripts\Get-Image.ps1 x86_64-pc-linux-gnu.x86_64-pc-linux-gnu.x86_64-w64-mingw32
 ```

This will fetch the `x86_64-pc-linux-gnu.x86_64-w64-mingw32.tar.gz` image and store it in an `IMAGES` subfolder of the folder you currently are in.

If something fails when building inside the contaier, there is a script `script\Debug-Container.ps1` that opens a `bash` prompt inside a build container, so you can examine the tmp folder.

## Versions 

### recipes-devtools

|  Package  |  Version |
|-----------|----------|
| binutils  | 2.31.1/2.28<sup>[1](#binutils-fn)</sup> |
| eglibc | 2.19 |
| expat | 2.1.0
| gcc | 8.2.0 |
| gdb | 8.1.1 |
| glibc | 2.28 / 2.24<sup>[2](#glibc-fn)</sup> |
| gmp | 6.1.2 |
| isl | 0.18 |
| linux-headers  | 3.2 / 4.9 / 4.14 / rpi3_4.9 |
| mingw-w64 | git (master) |
| mpc | 1.0.3 |
| mpfr | 3.1.6 |
| ncurses | 6.0 |
| newlib | 2.5.0 |

<a name="bintutils-fn">1</a>: glibc-4.9 is not compatible 
with binutils >= 2.29.

<a name="glibc-fn">2</a>: Raspbian currently relies on glibc-2.24

### recipes-gnat

|  Package  |  Version |
|-----------|----------|
| gprbuild | git (SRCREV=23f880d69854e4900248d923c9790057da44d492) |
| xmlada | git (master) |

## License

See [License](LICENSE)
