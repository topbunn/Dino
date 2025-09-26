package ru.topbun.feedback

data class FeedbackState(
    val email: String = "",
    val message: String = "",
    val feedbackState: FeedbackScreenState = FeedbackScreenState.Idle
){

    sealed interface FeedbackScreenState{
        object Idle: FeedbackScreenState
        object Loading: FeedbackScreenState
        object Success: FeedbackScreenState
        class Error(val message: String): FeedbackScreenState
    }

}
