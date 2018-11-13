FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "\
	${@bb.utils.contains("MACHINE_FEATURES", "linkdroid-pli", "file://revert-gamma-changes.patch", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "mecool-pli", "file://dvborder.patch file://revert-gamma-changes.patch", "", d)} \
"
