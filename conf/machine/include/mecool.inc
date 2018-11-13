KERNEL_FILE = "kernel.itb"
ROOTFS_FILE = "rootfs.tar.bz2"

PREFERRED_PROVIDER_virtual/kernel = "linux-coreelec"
PREFERRED_VERSION_linux-coreelec = "3.14.29%"

MACHINEKERNEL = "coreelec"

# Amlogic defaults
require conf/machine/include/arm/arch-armv8.inc
require conf/machine/include/soc-family.inc

IMAGE_CLASSES += "sdcard_image-amlogic"
SDCARD_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext4"
IMAGE_FSTYPES ?= "amlogicsdimg"
IMAGE_ROOTFS_SIZE = "476562"
IMAGE_EXTRA_SPACE = "0"
IMAGE_OVERHEAD_FACTOR = "1.0"

BB_HASH_IGNORE_MISMATCH = "1"

# Serial console to be used
SERIAL_CONSOLE = "115200 ttyS0"

KERNEL_CLASSES += "aarch64-fitimage"
KERNEL_IMAGETYPE = "fitImage"

UBOOT_ENTRYPOINT = "0x01080000"
UBOOT_LOADADDRESS = "0x01080000"

INITRAMFS_IMAGE = "amlogic-initramfs"

MACHINE_FEATURES = "bluetooth usbhost vfat alsa dvb-c nolcd hdmicec fb emmc mecool-pli"

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    amlogic-autoscript \
    linkdroid-mali-alien5 \
    linkdroid-gst-amlavsink1 \
    linkdroid-gst-app \
    linkdroid-libamplayer-alien5 \
    linkdroid-libamavutils-alien5 \
    linkdroid-libamadec-alien5 \
    linkdroid-libamcodec-alien5 \
    kernel-modules \
    amremote \
    mecool-e2-procfs \
    kernel-module-e2-procfs \
    kernel-module-aml \
    kernel-module-aml-fe \
    kernel-module-avl6862 \
    kernel-module-r848a \
    kernel-module-dhd \
    linkdroid-libavlib-audio-so-alien5 \
    libmad \
    mecool-amlsetfb \
    mecool-setup \
    firmware-ap6xxx-mecool \
    "

KERNEL_MODULE_AUTOLOAD += "\
    mali \
    rfcomm \
    hidp \
    bluetooth \
    6lowpan-iphc \
    libcrc32c \
    mceusb \
    rc-core \
    cfg80211 \
"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "\
    kernel-module-cdfs \
    kernel-module-sysfillrect \
    kernel-module-syscopyarea \
    kernel-module-mceusb \
    kernel-module-rc-core \
    kernel-module-CH341 \
    kernel-module-rfcomm \
    kernel-module-hidp \
    kernel-module-bluetooth \
    kernel-module-6lowpan-iphc \
    kernel-module-libcrc32c \
    kernel-module-xfs \
    kernel-module-cfg80211 \
    "

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

EXTRA_OECONF_append_pn-enigma2 = " --with-alsa --with-alsacard=hw:0 --with-alsamixer=Master --with-fbdev=/dev/fb0 --with-lcddev=/dev/null --with-amlogic"
DEPENDS_append_pn-enigma2 = " libdvbcsa linkdroid-libamcodec-alien5 linkdroid-libamadec-alien5 gstreamer1.0 gstreamer1.0-plugins-base "
EXTRA_OECONF_append_pn-tuxtxt-enigma2 = " --with-fbdev=/dev/fb0"
CC_append_pn-showiframe = " -DHAVE_AMLOGIC -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec -lgstpbutils-1.0 -lgstreamer-1.0 -lgobject-2.0 -lglib-2.0"
DEPENDS_append_pn-showiframe = " linkdroid-libamcodec-alien5 gstreamer1.0 gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly"

DISTRO_FEATURES_remove = "x11 wayland"

IMAGEDIR ?= "${MACHINE}"

IMAGE_CMD_amlogicsdimg_append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${IMAGE_NAME}.img; \
    echo ${DISTRO_NAME}-${DISTRO_VERSION}.${BUILD_VERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.json; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.img; \
    rm -Rf ${IMAGEDIR}; \
    "

# We know about rtc
include conf/machine/include/build-extras.inc

include conf/machine/include/enigma2-setup.inc
