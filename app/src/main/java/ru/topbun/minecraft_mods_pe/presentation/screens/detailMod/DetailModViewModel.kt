package ru.topbun.minecraft_mods_pe.presentation.screens.detailMod

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.topbun.domain.entity.FavoriteEntity
import ru.topbun.domain.entity.ModEntity
import ru.topbun.minecraft_mods_pe.repository.ModRepository

class DetailModViewModel(context: Context, mod: ModEntity): ViewModel() {

    private val repository = ModRepository(context)

    private val _state = MutableStateFlow(DetailModState(mod))
    val state get() = _state.asStateFlow()

    fun changeStageSetupMod(path: String?) = _state.update { it.copy(choiceFilePathSetup = path) }
    fun openDontWorkDialog(value: Boolean) = _state.update { it.copy(dontWorkAddonDialogIsOpen = value) }

    fun changeFavorite() = viewModelScope.launch{
        val mod = state.value.mod
        val favorite = repository.getFavoriteWithModId(mod.id) ?: FavoriteEntity(modId = mod.id, status = false)
        val newFavorite = favorite.copy(status = !favorite.status)
        repository.addFavorite(newFavorite)
        val newMod = _state.value.mod.copy(isFavorite = newFavorite.status, countFavorite = if (newFavorite.status) mod.countFavorite + 1 else mod.countFavorite - 1)
        _state.update { it.copy(mod = newMod) }
    }

}