package com.example.rock_paper_scissors_app.ui.feature_main.utils

sealed class UiEvent{
    data class OnNavigate(val route:String):UiEvent()
    data class ShowSnackBar(val message:String):UiEvent()
}
