package ru.topbun.data.api.dto

import ru.topbun.domain.entity.ModType

data class ModDto(
    val id: Int,
    val category: ModType,
    val rating: Double,
    val commentCounts: Int,
    val descriptionImages: List<String>,
    val title: String,
    val description: String,
    val image: String,
    val files: List<String>,
    val versions: List<String>,
)
