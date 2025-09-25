package ru.topbun.data.api.dto

import ru.topbun.domain.entity.ModEntity

data class GetModsResponse(
    val count: Int,
    val mods: List<ModDto>
)
