package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.GameButton
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

//*Initial State
@Composable
fun InitialSt(viewModel: GameViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Rounds",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primary)
            RoundsPicker(rounds = viewModel.state.roundsGame.value,
                plusRound = {viewModel.onEvent(GameEvents.PlusRound)},
                minusRound = {viewModel.onEvent(GameEvents.MinusRound)})
        }

        GameButton(text = "Accept") { viewModel.onEvent(GameEvents.ChangeGameState)}
    }
}
@Composable
fun RoundsPicker(
    rounds: Int,
    plusRound:() -> Unit,
    minusRound:() -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(rounds.toString(),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

        Column(){
            ImageButtonArrows(R.drawable.up_arrow){
                plusRound.invoke()
            }
            ImageButtonArrows(R.drawable.down_chevron){
                minusRound.invoke()
            }
        }
    }
}

@Composable
fun ImageButtonArrows(
    image:Int,
    action:()->Unit
) {
    Box(modifier = Modifier
        .clickable { action.invoke() }){
        Image(painter = painterResource(id = image),
            contentDescription = "Action")
    }
}


