package ru.topbun.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.topbun.data.database.entity.FavoriteDBO

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteDBO>

    @Query("SELECT * FROM favorites WHERE modId=:modId LIMIT 1")
    suspend fun getFavorite(modId: Int): FavoriteDBO?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addFavorite(favorite: FavoriteDBO)

}