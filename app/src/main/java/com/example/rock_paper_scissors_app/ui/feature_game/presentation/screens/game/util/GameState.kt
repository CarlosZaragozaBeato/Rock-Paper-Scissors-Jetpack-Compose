package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User

data class GameState (
    val currentPlayer:MutableState<User?> = mutableStateOf(null),
    val currentState:MutableState<ScreenState> = mutableStateOf(ScreenState.InitialState),
    val currentId: MutableState<String?> = mutableStateOf(null),
    val roundsGame:MutableState<Int> = mutableStateOf(1),
    val currentRound: MutableState<Int> = mutableStateOf(1),
    val playerScore:MutableState<Int> = mutableStateOf(0),
    val computerScore:MutableState<Int> = mutableStateOf(0),
    val playerSelection:MutableState<String?> = mutableStateOf(null),
    val computerSelection: MutableState<String?> = mutableStateOf(null),
    val gameState: MutableState<String?> = mutableStateOf(null) )