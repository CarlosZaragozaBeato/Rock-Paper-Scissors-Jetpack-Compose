package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.util.MenuEvents
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.util.MenuState
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val useCases: LoginUseCases
):ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state by mutableStateOf(MenuState())


    init {
        getUser()
    }


    fun onEvent(event: MenuEvents){
        when(event){
            is MenuEvents.Navigate -> sendUiEvent(UiEvent.OnNavigate(event.route))
            is MenuEvents.OnLogOut -> logOut()
        }
    }
    private fun getUser() = viewModelScope.launch (Dispatchers.IO){
        delay(100)
        useCases.getLogged.invoke().let { usuario ->
            state.currentUser.value = usuario
        }
    }

    private fun logOut() = viewModelScope.launch(Dispatchers.IO){
        useCases.insertUser.invoke(User(
            id = state.currentUser.value?.id,
            username = state.currentUser.value?.username.toString(),
            logged = 0))

        sendUiEvent(UiEvent.OnNavigate(route = Routes.LOGIN.name))
    }
    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch { _uiEvent.send(event) }
}