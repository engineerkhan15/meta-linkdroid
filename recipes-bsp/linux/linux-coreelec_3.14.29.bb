DESCRIPTION = "Amlogic Kernel (coreelec tree)"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel machine_kernel_pr
DEPENDS = "xz-native bc-native u-boot-mkimage-native virtual/${TARGET_PREFIX}gcc"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

COMPATIBLE_MACHINE = "k1pro|k2pro|k2prov2|k3pro|k1plus|kvim2"

DEPENDS_append_aarch64 = " libgcc"
KERNEL_CC_append_aarch64 = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append_aarch64 = " ${TOOLCHAIN_OPTIONS}"

S = "${WORKDIR}/linux-amlogic-amlogic-3.14-nougat"
B = "${WORKDIR}/build"

MACHINE_KERNEL_PR_append = ".20"

DTS = "${@ d.getVar('KERNEL_DEVICETREE').replace('.dtb','.dts') }"

SRC_URI = "https://github.com/OpenVisionE2/linux-amlogic/archive/amlogic-3.14-nougat.tar.gz \
  file://defconfig \
  file://openvision.patch \
  file://${DTS} \
"

SRC_URI[md5sum] = "4f3a2790f4052b7defa22b3746cbc193"
SRC_URI[sha256sum] = "50c0c5ffd993764dfe80a88e3d720a87a72840289c6c8cfbf6bcf5696be6fe80"

do_configure_prepend(){
    sed -i "s/@DISTRONAME@/${MACHINE}/" "${WORKDIR}/defconfig"
}

do_compile_append() {
    install -m 0644 ${WORKDIR}/${DTS} ${S}/arch/arm64/boot/dts/amlogic/
    if test -n "${KERNEL_DEVICETREE}"; then
    	for DTB in ${KERNEL_DEVICETREE}; do
    		if echo ${DTB} | grep -q '/dts/'; then
    			bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
    			DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
    		fi
    		oe_runmake ${DTB}
    	done
    	# Create directory, this is needed for out of tree builds
    	mkdir -p ${B}/arch/arm64/boot/dts/amlogic/
    	cp ${B}/arch/arm64/boot/dts/amlogic/${KERNEL_DEVICETREE} ${B}/arch/arm64/boot/
    fi
}
