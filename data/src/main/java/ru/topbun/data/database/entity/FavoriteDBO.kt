package ru.topbun.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites")
data class FavoriteDBO (

    @PrimaryKey(true)
    val id: Int = 0,
    val modId: Int,
    val status: Boolean

)