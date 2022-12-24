package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register.utils.RegisterEvents
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _name = mutableStateOf("")
    val name = _name

    private val _confirmName = mutableStateOf("")
    val confirmName = _confirmName

    public fun onEvent(event:RegisterEvents){
        when(event){
            is RegisterEvents.onRegister -> registerUser()
            is RegisterEvents.onChangeName -> _name.value = event.name
            is RegisterEvents.onChangeConfirmName -> _confirmName.value = event.name
            is RegisterEvents.Navigate -> sendUiEvent(UiEvent.OnNavigate(event.route))
        }
    }
    private fun registerUser() = viewModelScope.launch (Dispatchers.IO){
        if(_name.value.isNotBlank()){
            if(_confirmName.value.equals(_name.value)){
                loginUseCases.insertUser.invoke(User(username = _name.value, logged = 1))
                sendUiEvent(UiEvent.OnNavigate(Routes.MENU.name))
            } else sendUiEvent(UiEvent.ShowSnackBar(message = "Please enter the same values!"))
        }else{
            sendUiEvent(UiEvent.ShowSnackBar(message = "Please enter a valid name!"))
        }
    }
    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch { _uiEvent.send(event) }
}