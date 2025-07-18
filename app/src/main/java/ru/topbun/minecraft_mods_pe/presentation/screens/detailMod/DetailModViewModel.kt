package ru.topbun.minecraft_mods_pe.presentation.screens.detailMod

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.topbun.domain.entity.FavoriteEntity
import ru.topbun.domain.entity.ModEntity
import ru.topbun.minecraft_mods_pe.repository.ModRepository
import java.io.File

class DetailModViewModel(context: Context, mod: ModEntity): ViewModel() {

    private val repository = ModRepository(context)

    private val _state = MutableStateFlow(DetailModState(mod))
    val state get() = _state.asStateFlow()

    fun changeStageSetupMod(path: String?) = _state.update { it.copy(choiceFilePathSetup = path) }
    fun openDontWorkDialog(value: Boolean) = _state.update { it.copy(dontWorkAddonDialogIsOpen = value) }


    fun showInAppReview(activity: Activity) {
        val manager = ReviewManagerFactory.create(activity)
        val request = manager.requestReviewFlow()

        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                val flow = manager.launchReviewFlow(activity, reviewInfo)
                flow.addOnCompleteListener {

                }
            }
        }
    }

    fun installMod(context: Context, file: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/octet-stream")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            setPackage("com.mojang.minecraftpe")
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, context.getString(ru.topbun.domain.R.string.minecraft_is_not_installed), Toast.LENGTH_LONG).show()
        }
    }

    fun changeFavorite() = viewModelScope.launch{
        val mod = state.value.mod
        val favorite = repository.getFavoriteWithModId(mod.id) ?: FavoriteEntity(modId = mod.id, status = false)
        val newFavorite = favorite.copy(status = !favorite.status)
        repository.addFavorite(newFavorite)
        val newMod = _state.value.mod.copy(isFavorite = newFavorite.status, countFavorite = if (newFavorite.status) mod.countFavorite + 1 else mod.countFavorite - 1)
        _state.update { it.copy(mod = newMod) }
    }

}