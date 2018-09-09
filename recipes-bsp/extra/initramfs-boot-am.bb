SUMMARY = "Extremely basic live image init script for amlogic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro|k2prov2|k3pro|k1plus"

PR = "r4"

SRC_URI = "file://init \
	file://hdmi.sh \
	file://functions \
"

SRC_URI_alien5 = "file://init"

do_install() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/hdmi.sh  ${D}/etc/init.d/hdmi.sh
	install -m 0755 ${WORKDIR}/init  ${D}/init
	install -m 0755 ${WORKDIR}/functions  ${D}/functions
}

do_install_alien5() {
        install -m 0755 ${WORKDIR}/init  ${D}/init
}

inherit allarch

FILES_${PN} += "/init /etc/init.d/hdmi.sh /functions"
