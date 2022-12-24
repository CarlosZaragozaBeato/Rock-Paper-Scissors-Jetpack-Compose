package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components.*
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.ScreenState
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import com.example.rock_paper_scissors_app.ui.theme.spock


@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel(),
               onNavigate: (UiEvent.OnNavigate) -> Unit) {

    val state = viewModel.state


    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnNavigate -> onNavigate(event)
            }
        }
    }


    if(state.currentPlayer.value != null){
        Scaffold(
              topBar = {if(state.currentState.value == ScreenState.InitialState)
                  TopBarGame(name = state.currentPlayer.value!!.username, type = state.currentId.value.toString())}
        ) {
            when(state.currentState.value){
                is ScreenState.InitialState -> InitialSt(viewModel = viewModel)
                is ScreenState.GameState ->  GameSt(viewModel = viewModel)
                is ScreenState.RulesState -> RulesSt(viewModel = viewModel)
                is ScreenState.ResultState -> ResultSt(viewModel = viewModel)
                is ScreenState.FinalState -> FinalSt(viewModel = viewModel)
            }
        }
    }else{ CircularProgressIndicator() }
}
//*ToBar
@Composable
fun TopBarGame(name:String,
            type:String) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(name,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold)

            Text(type,
                style = MaterialTheme.typography.body1,
                color = spock,
                fontWeight = FontWeight.SemiBold)
        }
    }
}

