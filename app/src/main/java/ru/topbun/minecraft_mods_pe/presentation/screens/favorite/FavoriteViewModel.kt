package ru.topbun.minecraft_mods_pe.presentation.screens.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.topbun.domain.entity.FavoriteEntity
import ru.topbun.domain.entity.ModEntity
import ru.topbun.minecraft_mods_pe.repository.ModRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ModRepository(application)

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun removeFavorite(mod: ModEntity) = viewModelScope.launch{
        val favorite = repository.getFavoriteWithModId(mod.id) ?: FavoriteEntity(modId = mod.id, status = false)
        val newFavorite = favorite.copy(status = false)
        repository.addFavorite(newFavorite)
        val newMods = _state.value.mods.filter { it.id != mod.id }
        _state.update { it.copy(mods = newMods) }
    }

    private fun loadMods() = viewModelScope.launch{
        val mods = repository.getMods().filter { it.isFavorite }
        _state.update { it.copy(mods = mods) }
    }

    init {
        loadMods()
    }

}