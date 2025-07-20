package ru.topbun.domain.entity

import ru.topbun.ui.R

enum class SortType(val titleRes: Int) {

    ALL(R.string.sort_type_enum_all),
    ADDON(R.string.sort_type_enum_addon),
    MAPS(R.string.sort_type_enum_maps),
    TEXTURE(R.string.sort_type_enum_texture),
    SKINS(R.string.sort_type_enum_skins)

}