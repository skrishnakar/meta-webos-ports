SUMMARY = "Simple DirectMedia Layer"
DESCRIPTION = "Simple DirectMedia Layer is a cross-platform multimedia \
library designed to provide low level access to audio, keyboard, mouse, \
joystick, 3D hardware via OpenGL, and 2D video framebuffer."
HOMEPAGE = "http://www.libsdl.org"
BUGTRACKER = "http://bugzilla.libsdl.org/"

SECTION = "libs"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9956485e279291b03edb174e1975b762"

PROVIDES = "virtual/libsdl"

DEPENDS = "libffi wayland"

PV = "2.0.0+gitr${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/soreau/SDL.git;protocol=git;branch=wayland \
    file://0001-configure-check-wayland-egl-xkbcommon-wayland-egl-by.patch \
    file://0001-Makefile-kill-static-libs-creation-and-installation.patch \
"
SRCREV = "00cc3e4bd030960f7d2c21d8b218146dc96c4c20"
S = "${WORKDIR}/git"

inherit autotools lib_package binconfig pkgconfig

EXTRA_OECONF = "--disable-static --disable-debug --enable-cdrom --enable-threads --enable-timers --enable-endian \
                --enable-file --disable-oss --disable-esd --disable-arts \
                --disable-diskaudio --disable-nas --disable-esd-shared --disable-esdtest \
                --disable-mintaudio --disable-nasm --disable-video-dga \
                --disable-video-fbcon --disable-video-ps2gs --disable-video-ps3 \
                --disable-video-xbios --disable-video-gem --disable-video-dummy \
                --enable-input-events --enable-input-tslib --enable-pthreads \
                --disable-video-svga \
                --disable-video-picogui --disable-video-qtopia --enable-dlopen \
                --disable-rpath \
                --disable-pulseaudio"

EXTRA_OECONF += "--enable-video-wayland --enable-video-opengles"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)}"
PACKAGECONFIG[alsa] = "--enable-alsa --disable-alsatest,--disable-alsa,alsa-lib,"

PARALLEL_MAKE = ""

# include/SDL_config.h.in seems to be partially hand written (e.g. SDL_platform.h include)
# running autoheader breaks it
EXTRA_AUTORECONF += "--include=acinclude --exclude=autoheader"
