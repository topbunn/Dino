package ru.topbun.detail_mod

import ru.topbun.domain.entity.ModEntity

data class DetailModState(
    val mod: ModEntity,
    val choiceFilePathSetup: String? = null,
    val dontWorkAddonDialogIsOpen: Boolean = false,
)
