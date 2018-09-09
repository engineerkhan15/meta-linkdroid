FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "${@bb.utils.contains("MACHINE_FEATURES", "mecool-pli", "file://asound.conf", "", d)}"

#do_install() {
#	install -d ${D}${sysconfdir}
#	install -m 0755 ${WORKDIR}/asound.conf  ${D}${sysconfdir}/asound.conf
#}
