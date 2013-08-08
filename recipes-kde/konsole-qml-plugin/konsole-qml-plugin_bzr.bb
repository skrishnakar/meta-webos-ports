SUMMARY = "KDE Konsole QML plugin"
DESCRIPTION = "This package contains the Konsole terminal emulator plugin \
for Qt Qml, which offers access to terminal by KTerminal and \
KSession elements. KTerminal is used for displaying information \
given by KSession."
LICENSE = "GPL-3.0 & LGPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}/debian/copyright;md5=510d0264bac0330133e8fe07c6611a9a"

PV = "0.1.0+bzr${SRCPV}"

SRC_URI = "bzr://bazaar.launchpad.net/~hiroshidi/ubuntu-terminal-app/konsole-qml-plugin;protocol=http"
SRCREV = "10"
S = "${WORKDIR}/${PN}"

inherit qmake5

# Set path of qt5 headers as qmake5_base.bbclass sets this to just ${includedir} but
# actually it is ${includedir}/qt5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

FILES_${PN} += "${OE_QMAKE_PATH_QML}/org/kde/konsole/"
FILES_${PN}-dbg += "${OE_QMAKE_PATH_QML}/org/kde/konsole/.debug"
