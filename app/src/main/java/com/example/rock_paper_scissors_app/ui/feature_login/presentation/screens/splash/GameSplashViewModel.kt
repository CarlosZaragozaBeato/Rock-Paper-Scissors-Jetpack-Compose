package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameSplashViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
): ViewModel(){

    val user: MutableState<User?> = mutableStateOf(null)

    init {
        getUserLogged()
    }
    private  fun getUserLogged(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            loginUseCases.getLogged.invoke().let {
                user.value = it
            }
        }
    }


}