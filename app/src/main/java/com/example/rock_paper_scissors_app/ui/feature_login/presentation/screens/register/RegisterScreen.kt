package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register.utils.RegisterEvents
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.ButtonSection
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TextfieldCustom
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TitleComponent
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
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

    Scaffold (scaffoldState = scaffoldState){
        Column() {
            TitleComponent(text = "Register", modifier = Modifier.fillMaxHeight(.4f))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(.6f)
                    .fillMaxWidth()
            ){
               Column(modifier = Modifier.fillMaxWidth()) {

                   TextfieldCustom(
                       texto = viewModel.name.value,
                       placeholder = "Enter your name...",
                       modifier = Modifier
                   ){ name -> viewModel.onEvent(RegisterEvents.onChangeName(name))
                   }
                   Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

                   TextfieldCustom(
                       texto = viewModel.confirmName.value,
                       placeholder = "Confirm your name...",
                       modifier = Modifier
                   ){ confirmName -> viewModel.onEvent(RegisterEvents.onChangeConfirmName(confirmName)) }
               }
                ButtonSection(onNavigate = { viewModel.onEvent(RegisterEvents.Navigate(Routes.LOGIN.name))},
                            text = "Register",
                            option = "Already have an account?") {
                    viewModel.onEvent(RegisterEvents.onRegister) }
            }
        }
    }
}

