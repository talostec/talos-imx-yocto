# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2018 NXP

DESCRIPTION = "i.MX System Controller Firmware"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=72c0f70181bb6e83eee6aab8de12a9f3"
SECTION = "BSP"

inherit fsl-eula2-unpack2 pkgconfig deploy

SRC_URI[md5sum] = "6c92e112ef28d01fb7a716a7c2bde57e"
SRC_URI[sha256sum] = "dfa1d68071016007967cc65d1779c5696a9188e439b13c31a2c27ce29ef66606"

BOARD_TYPE ?= "mek"
SC_FIRMWARE_NAME ?= "mx8qm-mek-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qm = "mx8qm-${BOARD_TYPE}-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qxp = "mx8qx-${BOARD_TYPE}-scfw-tcm.bin"
symlink_name = "scfw_tcm.bin"

BOOT_TOOLS = "imx-boot-tools"

do_compile[noexec] = "1"

do_install[noexec] = "1"

do_deploy() {
    install -Dm 0644 ${S}/${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME}
    ln -sf ${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/${symlink_name}
}
addtask deploy after do_install

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(mx8qm|mx8qxp)"
