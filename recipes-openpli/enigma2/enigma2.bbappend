FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "\
	${@bb.utils.contains("MACHINE_FEATURES", "mecool-pli", "file://alien5-enigma2.patch", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "mecool-pli", "file://dvborder.patch", "", d)} \
	"
