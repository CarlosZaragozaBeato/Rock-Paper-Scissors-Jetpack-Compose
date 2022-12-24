package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.util.MenuEvents
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.TitleComponent
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

@Composable
fun MenuScreen(
    viewModel: MenuViewModel =
        hiltViewModel(),
    gameViewModel: GameViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit) {
    Scaffold {

        LaunchedEffect(key1 = true){
            viewModel.uiEvent.collect{ event ->
                when(event){
                    is UiEvent.OnNavigate -> onNavigate(event)
                    else ->Unit
                }
            }
            gameViewModel.reset()
        }
        val state = viewModel.state

        if(state.currentUser.value != null){
            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally){
                MenuHeader { viewModel.onEvent(MenuEvents.OnLogOut) }

                MenuGame(onBonus = { viewModel.onEvent(MenuEvents.Navigate(Routes.GAME.name+"/bonus")) },
                        onNormal = { viewModel.onEvent(MenuEvents.Navigate(Routes.GAME.name+"/normal")) },)

            }
        }else{
            CircularProgressIndicator()
        }
    }
}

@Composable
fun MenuHeader(onLogOut: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = LocalSpacing.current.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        TitleComponent(text = "Menu",
            alignment =  Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth(.8f)
                .fillMaxHeight(.1f))
        Image(painter = painterResource(id = R.drawable.log_out),
            contentDescription = "Log out",
            modifier = Modifier
                .size(LocalSpacing.current.large)
                .clickable {
                    onLogOut.invoke()
                })
    }
}

@Composable
fun ImageButton(
    image: Int,
    text:String,
    OnNavigate: () -> Unit
) {
    Row(modifier = Modifier
        .background(MaterialTheme.colors.primary)
        .fillMaxWidth()
        .height(74.dp)
        .clickable { OnNavigate.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Image(painter = painterResource(id = image),
            contentDescription = text,
            modifier = Modifier.size(64.dp))
        Text(text = text,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.background,
            fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun MenuGame(
    onBonus: () -> Unit,
    onNormal: () -> Unit
) {
    Column (modifier = Modifier
        .fillMaxHeight(.9f)
        .fillMaxWidth(.8f),
        verticalArrangement =Arrangement.Center){
        ImageButton(image = R.drawable.icon_rock,
            text = "Normal"){
                onNormal.invoke()
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.large))
        ImageButton(image = R.drawable.icon_lizard,
            text = "Bonus"){
                onBonus.invoke()
        } }
}