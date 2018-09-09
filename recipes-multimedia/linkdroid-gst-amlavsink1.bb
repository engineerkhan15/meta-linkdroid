SUMMARY = "GStreamer AML AVsink plugin"
#  Amlogic GStreamer plugins to send audio es to aml dsp and video es to aml hw decoder. 
#  decode and render will be complete at kernel level.
SECTION = "multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro|k2prov2|k3pro|k1plus"

inherit lib_package pkgconfig

### TODO:
## cram plugins as did not have time to check which ones provide gst/video headers 
## QA still complains about host headers used, does not make any sense
DEPENDS = " gstreamer1.0 linkdroid-libamcodec-alien5 linkdroid-libamavutils-alien5 gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly"


SRC_URI = "https://raw.githubusercontent.com/PLi-metas/amlogic-libs/master/gst-aml-plugins-1.0.zip"

SRC_URI[md5sum] = "335696a4b0e19f7528583fc31a6e654b"
SRC_URI[sha256sum] = "6256d664654d182881782e10a10537f127decb5318f2c546a279f02082c0a82c"

S = "${WORKDIR}/gst-aml-plugins-1.0"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${libdir}/gstreamer-1.0
    install -m 0755  ${WORKDIR}/gst-aml-plugins-1.0/libgstamlasink.so  ${D}${libdir}/gstreamer-1.0
    install -m 0755  ${WORKDIR}/gst-aml-plugins-1.0/libgstamlvsink.so  ${D}${libdir}/gstreamer-1.0
    install -m 0755  ${WORKDIR}/gst-aml-plugins-1.0/libgstamladec.so  ${D}${libdir}/gstreamer-1.0
    install -m 0755  ${WORKDIR}/gst-aml-plugins-1.0/libgstamlvdec.so  ${D}${libdir}/gstreamer-1.0
}

FILES_${PN} += "${libdir}/*"
FILES_${PN}-dev = ""
do_configure() {
}

do_compile() {
}

do_package_qa() {
}

INSANE_SKIP_${PN} = "already-stripped dev-so ldflags dev-deps"
SSTATE_DUPWHITELIST += "${STAGING_DIR}/usr/lib/gst-aml-plugins-1.0/libgstamlasink.so"
SSTATE_DUPWHITELIST += "${STAGING_DIR}/usr/lib/gst-aml-plugins-1.0/libgstamlvsink.so"
SSTATE_DUPWHITELIST += "${STAGING_DIR}/usr/lib/gst-aml-plugins-1.0/libgstamladec.so"
SSTATE_DUPWHITELIST += "${STAGING_DIR}/usr/lib/gst-aml-plugins-1.0/libgstamlvdec.so"
