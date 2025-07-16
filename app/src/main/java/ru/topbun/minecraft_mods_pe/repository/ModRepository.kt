package ru.topbun.minecraft_mods_pe.repository

import android.content.Context
import android.content.pm.PackageManager
import kotlinx.serialization.json.Json
import ru.topbun.domain.entity.FavoriteEntity
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.utills.getStringFromFileAssets
import ru.topbun.minecraft_mods_pe.data.database.AppDatabase

class ModRepository(private val context: Context){

    private val dao = AppDatabase.getInstance(context).favoriteDao()

    suspend fun getMods(): List<ModEntity> {
        val mods = loadMods()
        val favorites = dao.getFavorites()
        return mods.map { mod ->
            val favorite = favorites.firstOrNull { it.modId == mod.id }?.status == true
            mod.copy(isFavorite = favorite)
        }
    }

    fun getVersionMinecraft() = try {
        val packageInfo = context.packageManager.getPackageInfo("com.mojang.minecraftpe", 0)
        packageInfo.versionName.takeIf { it != "com.mojang.minecraftpe" }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }

    suspend fun getMod(id: Int): ModEntity {
        val mod = loadMods().first { it.id == id }
        val favoriteStatus = dao.getFavorite(id)?.status ?: false
        return mod.copy(isFavorite = favoriteStatus)
    }

    suspend fun getFavoriteWithModId(modId: Int) = dao.getFavorite(modId)


    suspend fun addFavorite(favorite: FavoriteEntity){
        dao.addFavorite(favorite)
    }

    private fun loadMods(): List<ModEntity> {
        val json = context.getStringFromFileAssets("json/skyblock_mods.json")
        return Json.decodeFromString<List<ModEntity>>(json)
    }

}