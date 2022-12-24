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
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.theme.*

//*GameState
@Composable
fun GameSt(viewModel: GameViewModel) {
    when(viewModel.state.currentId.value){
        "normal" -> NormalGame(viewModel = viewModel)
        "bonus" -> BonusGame(viewModel = viewModel)
    }
}
@Composable
fun NormalGame(viewModel: GameViewModel) {

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        ScoreSection(image = R.drawable.logo,
                        username = viewModel.state.currentPlayer.value?.username.toString(),
                        computerScore = viewModel.state.computerScore.value,
                        usernameScore = viewModel.state.playerScore.value)
        OptionsPanel { selection -> viewModel.onEvent(GameEvents.OpenResult(selection)) }
        RulesButton {
            viewModel.onEvent(GameEvents.OpenRules)
        }
        

    }
}

@Composable
fun RulesButton(onAction:()->Unit) {
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
            Text("Rules",
                style = MaterialTheme.typography.h5)
        }
}

@Composable
fun OptionsPanel(
    action: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            OptionBox(image = R.drawable.icon_paper,
                color = paper.toArgb()){action.invoke(Options.PAPER.name)}
            Spacer(modifier = Modifier.width(LocalSpacing.current.small))
            OptionBox(image = R.drawable.icon_scissors,
                color = scissors.toArgb()){ action.invoke(Options.SCISSORS.name) }
        }
        OptionBox(image = R.drawable.icon_rock,
            color = rock.toArgb()){action.invoke(Options.ROCK.name)}
    }
}

@Composable
fun OptionBox(
    image:Int,
    color:Int,
    action:() -> Unit
) {
    Box(modifier = Modifier
        .border(BorderStroke(width = 10.dp, color = Color(color)), shape = CircleShape)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primary)
        .size(120.dp)
        .clickable { action.invoke() },
        contentAlignment = Alignment.Center,
        ){
        Image(painter = painterResource(id = image) ,
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(.5f))
    }
}

@Composable
fun ScorePanel(
    player:String,
    score:Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = LocalSpacing.current.medium, horizontal = LocalSpacing.current.small)){
        Text(player,
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold)

        Text(score.toString(),
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold)}
}

@Composable
fun ScoreSection(
    username:String,
    usernameScore:Int,
    computerScore:Int,
    image:Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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

        Row(modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 5.dp, topEnd = 5.dp))
            .background(MaterialTheme.colors.primary)){

            ScorePanel(player = username,
                score = usernameScore)
            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
            ScorePanel(player = "Computer",
                score = computerScore)
        }
    }
}


@Composable
fun BonusGame(viewModel : GameViewModel) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        ScoreSection(image = R.drawable.logo_bonus,
            username = viewModel.state.currentPlayer.value?.username.toString(),
            computerScore = viewModel.state.computerScore.value,
            usernameScore = viewModel.state.playerScore.value)
        BonusPanel { selection ->
            viewModel.onEvent(GameEvents.OpenResult(selection))
        }
        RulesButton{
            viewModel.onEvent(GameEvents.OpenRules)
        }
    }
}

@Composable
fun BonusPanel(action:(String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxHeight(.8f)){
       Box(modifier = Modifier
           .fillMaxWidth(),
       contentAlignment = Alignment.Center){
           OptionBox(image = R.drawable.icon_scissors, color = scissors.toArgb() ) {
               action.invoke(Options.SCISSORS.name)
           }
       }
        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionBox(image =R.drawable.icon_spock , color = spock.toArgb() ) {
                action.invoke(Options.SPOCK.name)
            }
            OptionBox(image = R.drawable.icon_paper, color = paper.toArgb()) {
                action.invoke(Options.PAPER.name)
            }
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.large))
        Row(
            modifier = Modifier.fillMaxWidth(.7f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OptionBox(image = R.drawable.icon_lizard, color = lizzard.toArgb()) {
                action.invoke(Options.LIZARD.name)
            }
            OptionBox(image = R.drawable.icon_rock, color = rock.toArgb() ) {
                action.invoke(Options.ROCK.name)
            }
        }
    }
}