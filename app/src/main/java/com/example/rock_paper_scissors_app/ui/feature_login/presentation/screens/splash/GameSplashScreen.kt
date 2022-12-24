package com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing
import kotlinx.coroutines.delay

@Composable
fun GameSplashScreen(
    viewModel: GameSplashViewModel = hiltViewModel(),
    onPop: () -> Unit,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(key1 = true){
        delay(1500)
        onPop.invoke()
        if(viewModel.user.value == null){
            onNavigate.invoke(Routes.ACCESS.name)
        }else{
            onNavigate.invoke(Routes.MENU.name)
        }
    }

    Scaffold() {
        Column {
            TitleSplash()
            SecondPartSplash()
        }
    }
}

@Composable
fun TitleSplash() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.4f)
        .padding(start = LocalSpacing.current.small)){
        WordTitleSplash("Rock")
        WordTitleSplash("Paper")
        WordTitleSplash("Scissors")
    }
}

@Composable
fun WordTitleSplash(word:String) {
    Text(text = word,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.SemiBold)
}

@Composable
fun SecondPartSplash() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.6f),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.bg_pentagon),
            contentDescription = "Pentagon",
            modifier = Modifier.fillMaxSize(.8f))
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(.5f)){
            Image(painter = painterResource(id = R.drawable.icon_scissors),
                contentDescription = "Scissors Icon",
                modifier = Modifier.fillMaxSize(.5f))
        }
    }
}