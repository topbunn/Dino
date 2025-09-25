package ru.topbun.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.topbun.data.repository.ModRepository
import ru.topbun.domain.entity.ModEntity
import ru.topbun.domain.entity.ModType
import ru.topbun.main.ModSortEnum.ALL
import ru.topbun.main.ModSortEnum.BEST
import ru.topbun.main.ModSortEnum.NEW
import kotlin.collections.filter
import kotlin.collections.map

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = ModRepository(application)

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    val mods = _state.map {
        val modSort = it.modSorts[it.modSortSelectedIndex]
        val sortType = state.value.selectedSortType
        it.mods.filter {
            it.title.contains(state.value.search)
        }.sortedByDescending {
            when(modSort){
                BEST -> it.countDownload
                NEW -> it.id
                ALL -> it.title.first().code
            }
        }.filter {
            when(sortType){
                SortType.ALL -> true
                SortType.ADDON -> it.type.contains(ModType.ADDON)
                SortType.MAPS -> it.type.contains(ModType.WORLD)
                SortType.TEXTURE -> it.type.contains(ModType.TEXTURE_PACK)
                SortType.SKINS -> it.type.contains(ModType.SKIN_PACK)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, state.value.mods)

    fun changeOpenMod(mod: ModEntity?) = _state.update { it.copy(openMod = mod) }

    fun changeFavorite(mod: ModEntity) = viewModelScope.launch{
        val favorite = repository.getFavoriteWithModId(mod.id) ?: FavoriteEntity(modId = mod.id, status = false)
        val newFavorite = favorite.copy(status = !favorite.status)
        repository.addFavorite(newFavorite)
        val newMods = _state.value.mods.map {
            if (it.id == favorite.modId){ it.copy(isFavorite = newFavorite.status, countFavorite = if (newFavorite.status) it.countFavorite + 1 else it.countFavorite - 1) } else { it }
        }
        _state.update {
            it.copy(mods = newMods)
        }
    }

    fun changeSearch(value: String) = _state.update { it.copy(search = value) }
    fun changeModSort(selectedIndex: Int) = _state.update { it.copy(modSortSelectedIndex = selectedIndex) }
    fun changeSortType(sortType: SortType) = _state.update { it.copy(selectedSortType = sortType) }

    fun loadMods() = viewModelScope.launch{
        val mods = repository.getMods()
        _state.update { it.copy(mods = mods) }
    }

}