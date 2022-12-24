package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameViewModel
import com.example.rock_paper_scissors_app.R
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing

@Composable
fun RulesSt(
    viewModel: GameViewModel
) {
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Rules",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.background)
        when(viewModel.state.currentId.value){
            "normal" -> RulesImage(image = R.drawable.image_rules)
            "bonus" -> RulesImage(image = R.drawable.image_rules_bonus)
        }
        IconButton(onClick = { viewModel.onEvent(GameEvents.ChangeGameState) }) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                tint = MaterialTheme.colors.background,
                modifier = Modifier
                    .size(LocalSpacing.current.large))
        }
    }
}

@Composable
fun RulesImage(image:Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.7f),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = image),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(.8f),)
    }
}