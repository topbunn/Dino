package ru.topbun.main

import ru.topbun.domain.entity.ModEntity

data class MainState(
    val mods: List<ModEntity> = emptyList(),
    val openMod: ModEntity? = null,
    val search: String = "",
    val modSorts: List<ModSortEnum> = ModSortEnum.entries,
    val modSortSelectedIndex: Int = 0,
    val sortTypes: List<SortType> = SortType.entries,
    val selectedSortType: SortType = SortType.ALL
)
