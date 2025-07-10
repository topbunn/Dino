package ru.topbun.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites")
data class FavoriteEntity (

    @PrimaryKey(true)
    val id: Int = 0,
    val modId: Int,
    val status: Boolean

)