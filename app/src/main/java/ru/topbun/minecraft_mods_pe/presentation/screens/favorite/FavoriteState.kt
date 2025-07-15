package ru.topbun.minecraft_mods_pe.presentation.screens.favorite

import ru.topbun.domain.entity.ModEntity

data class FavoriteState(
    val mods: List<ModEntity> = emptyList(),
    val openMod: ModEntity? = null,
)
