package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.access

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.GameButton
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TitleComponent
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

@Composable
fun AccessScreen(
    onNavigate:(String) -> Unit
) {
    Scaffold(){
        Column(){
            TitleComponent("Access")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                GameButton(text = "Login"){ onNavigate.invoke(Routes.LOGIN.name) }
                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
                GameButton(text = "Register"){onNavigate.invoke(Routes.REGISTER.name) }
            }
        }
    }
}

