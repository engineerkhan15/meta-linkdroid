SUMMARY = "SysV init scripts for Amlogic devices set-up"
DESCRIPTION = "Provides basic set-up for amlogic devices"
SECTION = "base"

COMPATIBLE_MACHINE = "k1pro|k2pro|k2prov2|k3pro|k1plus"

include conf/license/license-gplv2.inc

PR = "r2"

inherit pkgconfig update-rc.d

INITSCRIPT_NAME = "mecool-setup"
INITSCRIPT_PARAMS = "start 07 S ."

INHIBIT_DEFAULT_DEPS = "1"
RDEPENDS_${PN} = "initscripts"

S = "${WORKDIR}"

SRC_URI = " \
    file://mecool-setup \
"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/mecool-setup  ${D}${sysconfdir}/init.d/mecool-setup
}
