package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login.utils

sealed class LoginEvents{
    data class Navigate(val route:String): LoginEvents()
    data class OnChangeName(val name:String): LoginEvents()
    object onLogin:LoginEvents()
}
