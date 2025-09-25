package ru.topbun.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class ModEntity(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val category: ModType,
    val isFavorite: Boolean,
    val rating: Double,
    val commentCounts: Int,
    val descriptionImages: List<String>,
    val files: List<String>,
    val versions: List<String>,
): Parcelable
