package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register.utils

sealed class RegisterEvents {
    data class onChangeName(val name:String):RegisterEvents()
    data class onChangeConfirmName(val name:String):RegisterEvents()
    object onRegister:RegisterEvents()
    data class Navigate(val route:String):RegisterEvents()
}