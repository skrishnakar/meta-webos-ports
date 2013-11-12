DESCRIPTION = "Apitrace is a tool to trace/reply/inspect OpenGL/OpenGL ES operations"
LICENSE = "Expat"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aeb969185a143c3c25130bc2c3ef9a50"

PV = "0.4.0+gitr${SRCPV}"

SRC_URI = " \
    git://github.com/apitrace/apitrace.git;branch=master;protocol=git \
    file://0001-Use-RGBA-format-in-getDrawBufferImage.patch \
    file://0002-Add-support-for-system-EGL-headers-and-enabling-EGL-.patch \
"
SRCREV = "084fe926e8fbbdf41f19d844157e4baac6a6b538"
S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -DENABLE_GUI:STRING=FALSE \
    -DSYSTEM_EGL:BOOL=true \
    -DENABLE_EGL_NO_X11:BOOL=true \
"

inherit pythonnative
inherit cmake

FILES_${PN} += "${libdir}/arm-linux-gnueabi/apitrace/wrappers"
FILES_${PN}-dbg += "${libdir}/arm-linux-gnueabi/apitrace/wrappers/.debug"
