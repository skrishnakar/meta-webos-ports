DESCRIPTION = "A QtMultimedia camera plugin that talks to Android"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "qtbase qtmultimedia qtubuntu-media-signals"

PV = "0.3.3+bzr${SRCPV}"

SRC_URI = " \
    bzr://bazaar.launchpad.net/~phablet-team/qtubuntu-camera/trunk;protocol=http \
    file://0001-Disable-components-we-don-t-want-to-build.patch \
    file://0002-Disable-use-of-ubuntu-platform-api.patch \
"
SRCREV = "83"
S = "${WORKDIR}/trunk"

inherit qmake5

# Set path of qt5 headers as qmake5_base.bbclass sets this to just ${includedir} but
# actually it is ${includedir}/qt5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"
