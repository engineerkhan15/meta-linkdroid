FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "${@bb.utils.contains("MACHINE_FEATURES", "mecool-pli", "file://0001-force-amlvsink-to-be-used-for-playing.patch", "", d)}"
