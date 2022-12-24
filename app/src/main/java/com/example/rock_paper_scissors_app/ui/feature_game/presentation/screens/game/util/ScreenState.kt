package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util

sealed class ScreenState  {
    object InitialState:ScreenState()
    object GameState:ScreenState()
    object RulesState:ScreenState()
    object ResultState:ScreenState()
    object FinalState:ScreenState()
}