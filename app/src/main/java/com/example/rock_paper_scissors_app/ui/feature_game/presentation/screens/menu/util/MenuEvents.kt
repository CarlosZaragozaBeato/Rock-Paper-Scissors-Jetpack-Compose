package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.util

sealed class MenuEvents {
    data class Navigate(val route:String):MenuEvents()
    object OnLogOut:MenuEvents()
}