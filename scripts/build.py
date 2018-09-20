#!/usr/bin/env python3

# This build script creates a build directory
# within meta-crosstools where bitbake is downloaded
# a local build environment is created and from which
# the bitbake command is run.

from os import path, environ
import os
import re
import sys
import logging
from urllib import request
import hashlib
import zipfile
import time
import subprocess
import argparse


logging.basicConfig(level=logging.WARNING, format='%(asctime)s %(message)s')

rootDir = path.normpath(path.join(path.realpath(__file__), '../../'))
logging.info('Root dir is "%s"' % rootDir)
BITBAKE_VERSION = '1.38.0'


def wspath(*args):
    return path.realpath(path.join(rootDir, *args))


def assurePath(*args):
    if path.isdir(wspath(*args)):
        logging.info("%s already exists." % wspath(*args))
    else:
        os.makedirs(wspath(*args), mode=0o777, exist_ok=1)
        logging.info("%s created." % wspath(*args))


def assureFile(content, *paths):
    dirs = paths[:len(paths)-1]
    assurePath(*dirs)
    if path.isfile(wspath(*paths)):
        logging.info("{} exists, will be overwritten.".format(wspath(*paths)))
    else:
        logging.info("{} will be created.".format(wspath(*paths)))
    with open(wspath(*paths), 'w') as f:
        f.write(content)


def calculateSha256(*paths):

    chunkSize = 4096
    h = hashlib.sha256()

    if path.isfile(path.isfile(wspath(*paths))):
        with open(wspath(*paths)) as f:
            while not f.closed:
                h.update(f.read(chunkSize))
    return h.hexdigest()


def assureHash(hash, *paths):
    # Given a hash, check the hash stored in the file at path
    if not path.isfile(wspath(*paths)):
        logging.info('Hash file {} does not exist.'.format(wspath(*paths)))
        return 0
    else:
        with open(wspath(*paths)) as f:
            return hash == f.read()


def getBitbakeEnv():
    bitbakeHome = wspath('build', 'bitbake-{}'.format(BITBAKE_VERSION))
    bbEnv = environ.copy()
    bbEnv["BITBAKE_HOME"] = bitbakeHome
    bbEnv["PATH"] = path.join(bitbakeHome, "bin") + ":" + bbEnv["PATH"]
    bbEnv["PYTHONPATH"] = path.join(
        bitbakeHome, "lib") + ":" + bbEnv.get("PYTHONPATH", "")
    logging.debug(bbEnv)
    return bbEnv


def download(url, *paths):
    # Download a file if it isn't already present

    chunkSize = 4096
    h = hashlib.sha256()

    if path.isfile(path.isfile(wspath(*paths))):
        logging.info(
            '"{}" already exists. Will not download.'.format(wspath(*paths)))
        return (False, calculateSha256(*paths))
    else:
        logging.info('"{f}" does not exist. Will download from "{d}".'.format(
            f=wspath(*paths), d=url))

        with request.urlopen(url) as inp, open(wspath(*paths), 'wb') as outp:
            while True:
                chunk = inp.read(chunkSize)
                if not chunk:
                    break
                h.update(chunk)
                outp.write(chunk)

        return (True, h.hexdigest())


def installBitBake():
    logging.info('Installing bitbake at {}'.format(wspath('build', 'bitbake')))

    assurePath('build', 'downloads')

    url = 'https://github.com/openembedded/bitbake/archive/{}.zip'.format(
        BITBAKE_VERSION)
    file = path.join('build', 'downloads',
                     'bitbake-{}.zip'.format(BITBAKE_VERSION))

    download(url, file)
    # hash is not used currently.

    # Unzip bitbake
    with zipfile.ZipFile(wspath(file), "r") as zf:
        zf.extractall(wspath('build'))

    # Make all files under bin executable
    bindir=wspath('build','bitbake-{}'.format(BITBAKE_VERSION),'bin')
    for file in os.listdir(bindir):
        os.chmod(path.join(bindir,file), 0o555)

    logging.info('Installing bitbake done.')

# STAMP HANDLING


def setStamp(stamp):
    assureFile(time.strftime('%x %X'), 'build', 'stamps', stamp)


def hasStamp(stamp):
    return path.isfile(wspath('build', 'stamps', stamp))


def makeStamp(target, recipe):
    return '{}-{}.stamp'.format(target, recipe)


def makeBuildEnv():
    # Make sure there is a build environment
    localConfFile = ('BB_NUMBER_THREADS := "12"\n'
                     'MAKE_JX := "-j12"\n')
    bbLayersFile = ('BBFILES ?= ""\n'
                    'BBLAYERS ?= "{mc_loc}"\n'.format(mc_loc=rootDir))

    assurePath('build')
    assurePath('build', 'conf')
    assureFile(localConfFile, 'build', 'conf', 'local.conf')
    assureFile(bbLayersFile, 'build', 'conf', 'bblayers.conf')

    # Install bitbake
    if not path.isdir(wspath('build', 'bitbake-{}'.format(BITBAKE_VERSION))):
        installBitBake()


def getTargetConfFile(target):
    return wspath("conf", "toolchains", target + ".conf")


def getDependencies(target):
    print("Target = %s" % target)
    targetFile = getTargetConfFile(target)
    with open(targetFile) as f:
        confData = f.read()

    m = re.match(r"^#\s* ///\s*DEPENDENCIES=(.*)", confData)
    if not m:
        return ""
    else:
        return m.group(1).split(',')


def runBitbake(target, recipe):
    logging.info('BEGIN runBitbake {target} {recipe}'.format(
        target=target, recipe=recipe))
    stamp = makeStamp(target, recipe)
    if hasStamp(stamp):
        logging.info('{} already done. Skipping'.format(stamp))
        print('{} already built. Skipping'.format(target))
        return

    deps = getDependencies(target)
    for d in deps:
        runBitbake(d, 'image')
    buildDir = wspath('build')
    print('Building recipe {} for {}'.format(recipe, target))
    proc = subprocess.run(args=['bitbake', '-R', getTargetConfFile(target), recipe],
                          cwd=buildDir,
                          env=getBitbakeEnv())
    if proc.returncode == 0:
        setStamp(stamp)
    else:
        logging.error("Error building recipe. Exiting.")
        raise SystemExit
    logging.info('END runBitbake {target} {recipe}'.format(
        target=target, recipe=recipe))
    print('Recipe "{}" baked for target "{}"!'.format(recipe,target))


def main():

    parser = argparse.ArgumentParser(
        description="Build a toolchain by specifying the target and the recipe to call on that target.",
        epilog='For instance, "scripts/build.py image native-mipsel mingw-rpi3" would result in that'
        ' the image recipe would be built for the following '
        'targets: native, native-mipsel, native-rpi3, native-mingw, mingw-rpi3')
    parser.add_argument("-v", "--verbosity", help="Display more information",
                        action="store_true")
    parser.add_argument("recipe", help="the recipe to run for all targets")
    parser.add_argument("targets", metavar='target', nargs="+",
                        help="the targets to apply the recipe on")
    args = parser.parse_args()

    if args.verbosity:
        logging.getLogger().setLevel(logging.DEBUG)

    makeBuildEnv()
    # build.py recipe targets...
    for t in args.targets:
        runBitbake(t, args.recipe)


if __name__ == "__main__":
    main()