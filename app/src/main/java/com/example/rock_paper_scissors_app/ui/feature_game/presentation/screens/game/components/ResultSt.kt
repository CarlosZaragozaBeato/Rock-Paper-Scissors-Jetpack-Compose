package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.theme.*

@Composable
fun ResultSt(viewModel: GameViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        RoundBar(image = R.drawable.logo, currentRound = viewModel.state.currentRound.value)
        PlayerOptions(viewModel = viewModel)
        RoundState(state = viewModel.state.gameState.value.toString())
        NextButton { viewModel.onEvent(GameEvents.NextAction) }
    }
}

@Composable
fun NextButton(onAction:()->Unit) {
    Box(
        modifier = Modifier
            .clickable { onAction.invoke() }
            .width(100.dp)
            .height(40.dp)
            .border(
                BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(LocalSpacing.current.small)
            )
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text("Next",
            style = MaterialTheme.typography.h5)
    }
}

@Composable
fun PlayerOptions(viewModel: GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BoxOption(player = viewModel.state.currentPlayer.value?.username.toString(),
                  selection = viewModel.state.playerSelection.value.toString())
        Spacer(modifier = Modifier.width(LocalSpacing.current.small))
        BoxOption(player = "Computer",
            selection = viewModel.state.computerSelection.value.toString() )
    }
}

@Composable
fun RoundState(state:String) {
    Box(modifier = Modifier
        .fillMaxHeight(.3f)){
        Text(state,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary)
    }
}

@Composable
fun BoxOption( player:String,
            selection :String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            when(selection){
                  Options.ROCK.name -> {
                     ResultBox(color = rock.toArgb(), image = R.drawable.icon_rock)
                 }
                 Options.PAPER.name -> {
                     ResultBox(color = paper.toArgb(), image = R.drawable.icon_paper)
                }
                 Options.SCISSORS.name -> {
                     ResultBox(color = scissors.toArgb(), image = R.drawable.icon_scissors)
                }
                Options.SPOCK.name -> {
                    ResultBox(color = spock.toArgb(), image = R.drawable.icon_spock)
                }
                Options.LIZARD.name -> {
                    ResultBox(color = lizzard.toArgb(), image = R.drawable.icon_lizard)
                }
            }
        Spacer(modifier = Modifier.padding(vertical = LocalSpacing.current.small))
        Text(text = player,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.primary)
    }
}

@Composable
fun ResultBox(image:Int,
    color:Int, ) {
    Box(modifier = Modifier
        .border(BorderStroke(width = 10.dp, color = Color(color)), shape = CircleShape)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primary)
        .size(120.dp),
        contentAlignment = Alignment.Center,
    ){
        Image(painter = painterResource(id = image) ,
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(.5f))
    }
}

@Composable
fun RoundBar(image:Int,
    currentRound:Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.115f)
            .padding(
                horizontal = LocalSpacing.current.medium,
                vertical = LocalSpacing.current.small
            )
            .border(
                BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(5.dp)
            )
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(id = image),
            contentDescription = "Game logo",
            modifier = Modifier
                .size(64.dp)
                .padding(start = LocalSpacing.current.small))

        Column(modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(bottomEnd = 5.dp, topEnd = 5.dp))
            .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Text("Rounds",
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small))
            Text(currentRound.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.background)
        }
    }
}