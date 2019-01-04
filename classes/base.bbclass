runmake() {
    echo ${MAKE} "$@"
	${MAKE} "$@"
}

addtask fetch
do_fetch[dirs] = "${DL_DIR}"
do_fetch[file-checksums] = "${@bb.fetch.get_checksum_file_list(d)}"
do_fetch[vardeps] += "SRCREV"

python base_do_fetch() {

    src_uri = (d.getVar('SRC_URI', True) or "").split()
    if len(src_uri) == 0:
        return

    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
    except bb.fetch2.BBFetchException as e:
        raise bb.build.FuncFailed(e)
}

addtask unpack after do_fetch
do_unpack[dirs] = "${WORKDIR}"
python () {
    if d.getVar('S', True) != d.getVar('WORKDIR', True):
        d.setVarFlag('do_unpack', 'cleandirs', '${S}')
    else:
        d.setVarFlag('do_unpack', 'cleandirs', os.path.join('${S}', 'patches'))
}

python base_do_unpack() {
    src_uri = (d.getVar('SRC_URI', True) or "").split()
    if len(src_uri) == 0:
        return

    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.unpack(d.getVar('WORKDIR', True))
    except bb.fetch2.BBFetchException as e:
        raise bb.build.FuncFailed(e)
}

addtask patch after do_unpack
do_patch[dirs] = "${WORKDIR}"
base_do_patch () {
  DIR=$(dirname "${FILE}")
	if [ -d "${DIR}/patches" ]
	then
		cd ${S}
		for i in ${DIR}/patches/*.patch; do
      test -f "$i" || continue
			patch -p1 < $i;
			echo "Applied patch: $i"
		done
	fi
}

addtask configure after do_patch
do_configure[dirs] = "${B}"
do_configure[deptask] = "do_install"
base_do_configure() {
 :
}

addtask compile after do_configure
do_compile[dirs] = "${B}"
base_do_compile() {
	if [ -e Makefile -o -e makefile -o -e GNUmakefile ]; then
		runmake ${MAKE_JX} all
	else
		echo "nothing to compile"
	fi
}

addtask install after do_compile
do_install[dirs] = "${B}"
base_do_install() {
	runmake install
}

addtask build after do_install
do_build[dirs] = "${TOPDIR}"
do_build () {
	echo "do_build"
}

addtask do_clean
python base_do_clean () {
    
    import shutil
    import glob

    # First, make sure this task is not stamped. It will always run...
    d.setVarFlag('do_clean', 'nostamp', 'True')

    # Then we need to close the fifo file, so that we can
    # erase the work directory
    tempdir = d.getVar('T')
    fifopath = os.path.join(tempdir, 'fifo.%s' % os.getpid())
    if os.path.exists(fifopath):
        os.unlink(fifopath)
    
    # Delete all stamps that are associated with the recipe
    stamps = d.getVar('STAMP') + ".*"
    for f in glob.glob(stamps):
        os.remove(f)

    workdir = d.getVar('WORKDIR')
    try:
        shutil.rmtree(workdir)
    except Exception as e:
        bb.warn("Exception: {excp}".format(excp=e))
    bb.plain("Cleaned recipe " + d.getVar('PN'))
}

EXPORT_FUNCTIONS do_fetch do_unpack do_configure do_compile do_install do_package do_build do_patch do_clean
