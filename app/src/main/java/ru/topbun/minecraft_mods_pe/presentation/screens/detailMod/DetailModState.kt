package ru.topbun.minecraft_mods_pe.presentation.screens.detailMod

import ru.topbun.domain.entity.ModEntity

data class DetailModState(
    val mod: ModEntity,
    val choiceFilePathSetup: String? = null,
    val dontWorkAddonDialogIsOpen: Boolean = false,
)
