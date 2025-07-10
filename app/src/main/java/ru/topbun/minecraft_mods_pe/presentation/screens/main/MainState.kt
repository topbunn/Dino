package ru.topbun.minecraft_mods_pe.presentation.screens.main

import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.entity.SortType

data class MainState(
    val mods: List<ModEntity> = emptyList(),
    val search: String = "",
    val modSorts: List<ModSortEnum> = ModSortEnum.entries,
    val modSortSelectedIndex: Int = 0,
    val sortTypes: List<SortType> = SortType.entries,
    val selectedSortType: SortType = SortType.ALL
)
