package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util

sealed class GameEvents {
    object PlusRound : GameEvents()
    object MinusRound : GameEvents()
    object ChangeGameState : GameEvents()
    object OpenRules : GameEvents()
    data class OpenResult(val selection: String) : GameEvents()
    object NextAction: GameEvents()
    object ToMenu:GameEvents()
}