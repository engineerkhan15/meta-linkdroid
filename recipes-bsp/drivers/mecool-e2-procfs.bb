SUMMARY = "Mecool e2-procfs for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=d32239bcb673463ab874e80d47fae504"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "k1pro|k2pro|k2prov2|k3pro|k1plus"

SRCREV = "${AUTOREV}"

PR = "r1"

SRC_URI = "git://github.com/PLi-metas/amlogic-e2-procfs.git;protocol=git"

S = "${WORKDIR}/git/source/e2_procfs"

inherit module

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C "${STAGING_KERNEL_BUILDDIR}" M="${S}" modules
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/amlogic/enigma2
	install -m 0644 ${S}/e2_procfs.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/amlogic/enigma2/
}
