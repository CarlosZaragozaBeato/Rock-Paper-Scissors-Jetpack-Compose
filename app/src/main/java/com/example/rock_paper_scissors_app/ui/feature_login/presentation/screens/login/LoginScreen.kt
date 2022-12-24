package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login.utils.LoginEvents
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.ButtonSection
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TextfieldCustom
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TitleComponent
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate:(UiEvent.OnNavigate) -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is  UiEvent.OnNavigate -> onNavigate(event)
                is UiEvent.ShowSnackBar ->scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column() {
            TitleComponent(text = "Login", modifier = Modifier.fillMaxHeight(.4f))
            Column(){
                TextfieldCustom(texto = viewModel.name.value,
                    placeholder = "Enter a name..."){ name ->
                    viewModel.onEvent(LoginEvents.OnChangeName(name))
                }
                ButtonSection(onNavigate = { viewModel.onEvent(LoginEvents.Navigate(Routes.REGISTER.name)) },
                    option = "doesn't have an account yet?" , text = "Login") {
                        viewModel.onEvent(LoginEvents.onLogin)
                }
            }

        }
    }
}
