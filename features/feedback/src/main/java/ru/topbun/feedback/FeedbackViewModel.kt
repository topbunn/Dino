package ru.topbun.feedback

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
import ru.topbun.android.utills.isEmail
import ru.topbun.data.repository.ModRepository
import ru.topbun.domain.entity.IssueEntity

class FeedbackViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ModRepository(application)

    private val _state = MutableStateFlow(FeedbackState())
    val state = _state.asStateFlow()

    fun changeEmail(email: String) = _state.update { it.copy(email = email) }
    fun changeMessage(message: String) = _state.update { it.copy(message = message) }

    fun sendIssue() = viewModelScope.launch {
        _state.update { it.copy(feedbackState = FeedbackState.FeedbackScreenState.Loading) }
        val issue = IssueEntity(email = state.value.email, text = state.value.message)
        val result = repository.sendIssue(issue)
        result.onSuccess {
            _state.update { it.copy(feedbackState = FeedbackState.FeedbackScreenState.Success, email = "", message = "") }
        }.onFailure {
            _state.update { it.copy(feedbackState = FeedbackState.FeedbackScreenState.Error(it.message)) }
        }
    }

    val buttonEnabled = _state.map {
        it.email.isEmail() && it.message.length in (32..1024)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

}