package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils.GameButton
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

@Composable
fun FinalSt(viewModel: GameViewModel) {

  Column(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {
      WinnerState(
          player = viewModel.state.playerScore.value,
          Computer = viewModel.state.computerScore.value,
          currentPlayer = viewModel.state.currentPlayer.value?.username.toString())

      Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

      GameButton(text = "Finish") {
          viewModel.onEvent(GameEvents.ToMenu)
      }
  }

}

@Composable
fun WinnerState(player:Int,
                Computer:Int,
                currentPlayer:String) {
        Text(
            if(Computer>player) "Computer Wins"
            else if (Computer<player)"${currentPlayer} Wins"
            else "Draw",
            style = MaterialTheme.typography.h3)

}

