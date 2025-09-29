package ru.topbun.detail_mod

import ru.topbun.domain.entity.ModEntity

data class DetailModState(
    val mod: ModEntity,
    val choiceFilePathSetup: String? = null,
    val dontWorkAddonDialogIsOpen: Boolean = false,
    val descriptionImageExpand: Boolean = false,
    val downloadState: DownloadModState = DownloadModState.Idle
){

    sealed interface DownloadModState{

        data object Idle: DownloadModState
        data class Loading(val progress: Int): DownloadModState
        data class Error(val message: String): DownloadModState
        data object Success: DownloadModState

    }

}
