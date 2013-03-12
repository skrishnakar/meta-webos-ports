# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.
# Copyright (c) 2013 LG Electronics, Inc.

SUMMARY = "Open webOS logging library"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 libpbnjson"

# corresponds to tag submissions/29
SRCREV = "44f35c663295845a30c1541b7afcc86004c09570"
PV = "3.0.0-29"
PR = "r5"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_library

WEBOS_GIT_TAG = "submissions/${WEBOS_SUBMISSION}"
SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
