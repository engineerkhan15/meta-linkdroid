SUMMARY = "GStreamer AML player app"
SECTION = "multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro|k2prov2|k3pro|k1plus|kvim2"

DEPENDS = "gstreamer libffi zlib libxml2 glib-2.0"

SRC_URI = "https://raw.githubusercontent.com/OpenVisionE2/amlogic-libs/master/gst-app-0.11.0.zip"

SRC_URI[md5sum] = "be89f67888854239ec4fea4dd6dcb10f"
SRC_URI[sha256sum] = "13a2992f30db55eda6e3f655d6fb0635eb9a5976abb17cbd90761c29f07489d4"

S = "${WORKDIR}/gst-app-0.11.0"

inherit autotools pkgconfig

do_configure_prepend() {
    # This m4 file contains nastiness which conflicts with libtool 2.2.2
    rm ${S}/m4/lib-link.m4 || true
}
