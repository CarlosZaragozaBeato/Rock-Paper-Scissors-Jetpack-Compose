package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login.utils.LoginEvents
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
class LoginViewModel @Inject constructor(
    private val loginUseCases:LoginUseCases
): ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _name = mutableStateOf("")
    val name = _name


    public fun onEvent(event: LoginEvents){
        when(event){
            is LoginEvents.onLogin -> loginUser()
            is LoginEvents.OnChangeName -> _name.value = event.name
            is LoginEvents.Navigate -> sendUiEvent(UiEvent.OnNavigate(event.route))
        }
    }
    private fun loginUser() = viewModelScope.launch (Dispatchers.IO){
        if(_name.value.isNotBlank()){
            loginUseCases.getUser.invoke(_name.value).let {user ->
                if(user != null){
                    loginUseCases.insertUser(User(
                        id = user.id,
                        username = user.username,
                        logged = 1
                    ))
                    sendUiEvent(UiEvent.OnNavigate(Routes.MENU.name))
                }else{
                    sendUiEvent(UiEvent.ShowSnackBar(message = "The name is incorrect!"))
                }
            }
        }else{
            sendUiEvent(UiEvent.ShowSnackBar(message = "Please enter a valid name!"))
    }
    }
    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch { _uiEvent.send(event) }
}
