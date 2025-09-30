package ru.topbun.data.repository

import android.content.Context
import ru.topbun.android.utills.getModNameFromUrl
import ru.topbun.data.DownloadState
import ru.topbun.data.api.ApiFactory
import ru.topbun.data.database.AppDatabase
import ru.topbun.data.database.entity.FavoriteEntity
import ru.topbun.data.mappers.ModMapper
import ru.topbun.data.saveFile
import ru.topbun.domain.entity.IssueEntity
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.entity.ModSortType
import ru.topbun.domain.entity.ModType

class ModRepository(context: Context) {

    private val favoriteDao = AppDatabase.getInstance(context).favoriteDao()
    private val api = ApiFactory.api
    private val modMapper = ModMapper(context)

    suspend fun downloadFile(url: String, filename: String) = runCatching {
        api.downloadFile(url).saveFile(filename)
    }

    suspend fun getMods(
        q: String,
        offset: Int,
        type: ModType?,
        sortType: ModSortType,
    ) = runCatching {
        val response = api.getMods(
            q = q,
            skip = offset,
            category = type,
            sortKey = sortType.toString(),
        )
        modMapper.toEntity(response.mods)
    }


    suspend fun getMod(id: Int) = runCatching {
        val mod = api.getMod(id)
        modMapper.toEntity(mod)
    }

    suspend fun getFavoriteMods() = runCatching {
        val favoriteIds = favoriteDao.getFavorites().map { it.modId }
        val mods = mutableListOf<ModEntity>()
        favoriteIds.forEach {
            try {
                val mod = api.getMod(it)
                mods.add(modMapper.toEntity(mod))
            } catch (_: Exception){}
        }
       return@runCatching mods
    }


    suspend fun addFavorite(favorite: FavoriteEntity) {
        val oldFavorite = favoriteDao.getFavorite(favorite.modId)
       favoriteDao.addFavorite(favorite.copy(id = oldFavorite?.id ?: 0))
    }

    suspend fun sendIssue(issue: IssueEntity) = runCatching{
        api.createIssue(issue = issue)
    }


}