SUMMARY = "GStreamer AML AVout plugin"
# Freescale rip-off
#  Amlogic GStreamer plugins to send audio es to aml dsp and video es to aml hw decoder. 
#  decode and render will be complete at kernel level.
SECTION = "multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro|k2prov2|k3pro|k1plus"

PARALLEL_MAKE = ""

inherit autotools pkgconfig

### TODO: code check!
### QA Issue: gst-amlavout: The compile log indicates that host include and/or library paths were used.
### -libplayer issue  warning: library search path "/usr/lib/libplayer" need to add it to DEPENDES
### *.la probably not needed as these are gst-plugins

DEPENDS = "gstreamer linkdroid-gst-amlavsink linkdroid-libamcodec-alien5 linkdroid-libamavutils-alien5 linkdroid-libamplayer-alien5"
RDEPENDS_{PN} = "linkdroid-libamavutils-alien5"

SRC_URI = "https://raw.githubusercontent.com/PLi-metas/amlogic-libs/master/gst-aml-plugins-0.10.0-linkdroid.zip"

SRC_URI[md5sum] = "907c4f49be34ee0b16b3de28ea2c5ca9"
SRC_URI[sha256sum] = "a450ff9607b5e99ad8e29bcd0288570492f1fefa08765100358795fffa55f535"

S = "${WORKDIR}/gst-aml-plugins-0.10.0"

CFLAGS =+ " -O2 -fPIC -I${STAGING_INCDIR} -I${S}/include  -I${S}/include/amports  -I${S}/common/include/ppmgr \
-I${S}/amlaudio -I${S}/amlvideo "
LDFLAGS =+ " -L${STAGING_LIBDIR} -L${STAGING_BASELIBDIR} " 

FILES_${PN} += "${libdir}"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug $"
FILES_${PN}-dev += "${S}/include/* "

do_rm_work(){
}