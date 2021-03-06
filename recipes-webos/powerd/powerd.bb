# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Power policy daemon"
AUTHOR = "Keith Derrick <keith.derrick@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "nyx-lib luna-service2 cjson glib-2.0"

WEBOS_VERSION = "4.0.0-25_87309ff87bf5d3d6c54095075be9acdf3e403a2c"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library
inherit webos_daemon
inherit webos_system_bus

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
