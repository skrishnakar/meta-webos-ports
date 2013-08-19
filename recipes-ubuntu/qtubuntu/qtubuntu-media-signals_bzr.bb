DESCRIPTION = "Library that coordinates media libraries across thread contexts \
Library that coordinates qtvideo-node, qtubuntu-camera and qtubuntu-media \
across thread contexts."
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

DEPENDS = "qtbase"

PV = "0.3+bzr${SRCPV}"

SRC_URI = "bzr://bazaar.launchpad.net/~phablet-team/qtubuntu-media-signals/trunk;protocol=http"
SRCREV = "10"
S = "${WORKDIR}/trunk"

inherit cmake_qt5
