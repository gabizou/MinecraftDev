/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2021 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.forge.inspections.sideonly

import com.demonwav.mcdev.platform.forge.util.ForgeConstants

data class SideAnnotation(
    val annotationName: String,
    val enumName: String,
    val clientValue: String,
    val serverValue: String,
) {
    val simpleEnumName: String = enumName.substringAfterLast('.')

    fun renderSide(side: Side): String? = when (side) {
        Side.CLIENT -> "$simpleEnumName.$clientValue"
        Side.SERVER -> "$simpleEnumName.$serverValue"
        else -> null
    }

    fun renderFQSide(side: Side): String? = when (side) {
        Side.CLIENT -> "$enumName.$clientValue"
        Side.SERVER -> "$enumName.$serverValue"
        else -> null
    }

    companion object {
        val KNOWN_ANNOTATIONS = listOf(
            SideAnnotation(
                ForgeConstants.SIDE_ONLY_ANNOTATION,
                ForgeConstants.SIDE_ANNOTATION,
                "CLIENT",
                "SERVER"
            ),
            SideAnnotation(
                "cpw.mods.fml.relauncher.SideOnly",
                "cpw.mods.fml.relauncher.Side",
                "CLIENT",
                "SERVER"
            ),
            SideAnnotation(
                "net.minecraftforge.api.distmarker.OnlyIn",
                "net.minecraftforge.api.distmarker.Dist",
                "CLIENT",
                "DEDICATED_SERVER"
            ),
        )
    }
}
