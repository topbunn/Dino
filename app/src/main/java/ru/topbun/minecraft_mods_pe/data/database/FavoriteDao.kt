package ru.topbun.minecraft_mods_pe.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import ru.topbun.domain.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Query("SELECT * FROM favorites WHERE modId=:modId LIMIT 1")
    suspend fun getFavorite(modId: Int): FavoriteEntity?

    @Insert(onConflict = REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

}