package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User

data class MenuState (val currentUser: MutableState<User?> = mutableStateOf(null))
