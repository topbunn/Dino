package ru.topbun.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ModEntity(
    val id: Int,
    val title: String,
    val description: String,
    val previewRes: String,
    val isFavorite: Boolean = false,
    val countDownload: Int,
    val countFavorite: Int,
    val tag: ModTag,
    val type: List<ModType>,
    val supportVersion: List<String>,
    val files: List<String>,
)
