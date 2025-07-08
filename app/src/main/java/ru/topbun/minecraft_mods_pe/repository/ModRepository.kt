package ru.topbun.minecraft_mods_pe.repository

import android.content.Context
import kotlinx.serialization.json.Json
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.utills.getStringFromFileAssets

class ModRepository(private val context: Context){

    fun getMods(): List<ModEntity> {
        val json = context.getStringFromFileAssets("json/skyblock_mods.json")
        return Json.decodeFromString<List<ModEntity>>(json)
    }

}