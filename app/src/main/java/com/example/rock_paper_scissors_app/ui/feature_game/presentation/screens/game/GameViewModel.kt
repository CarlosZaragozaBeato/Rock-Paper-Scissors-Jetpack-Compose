package com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.components.Options
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameEvents
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.GameState
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.util.ScreenState
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.Routes
import com.example.rock_paper_scissors_app.ui.feature_main.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase:LoginUseCases
    ): ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state by  mutableStateOf(GameState())

    private val normalOptions:List<String> = listOf(Options.PAPER.name,
                                            Options.ROCK.name,
                                            Options.SCISSORS.name)

    private val bonusOptions:List<String> = listOf(Options.PAPER.name,
                                            Options.ROCK.name,
                                            Options.SCISSORS.name,
                                            Options.LIZARD.name,
                                            Options.SPOCK.name)

    init {
        viewModelScope.launch {
            state.currentId.value = savedStateHandle.get<String>("type")
            getUser()
        }
    }

    fun onEvent(event: GameEvents){
        when(event){
            is GameEvents.MinusRound -> if(state.roundsGame.value>=1) state.roundsGame.value -=1
            is GameEvents.PlusRound -> if(state.roundsGame.value<15) state.roundsGame.value +=1
            is GameEvents.ChangeGameState -> state.currentState.value = ScreenState.GameState
            is GameEvents.OpenRules -> state.currentState.value = ScreenState.RulesState
            is GameEvents.OpenResult -> gameResult(event.selection)
            is GameEvents.NextAction -> NextState()
            is GameEvents.ToMenu -> toMenu()
        }
    }

    private fun gameResult(playerSelection: String){
        viewModelScope.launch {

            state.playerSelection.value = playerSelection
            state.currentState.value = ScreenState.ResultState
            if(state.currentId.value == "normal"){
                state.computerSelection.value = normalOptions.random()
                gameNormalLogic()
            }
            else if(state.currentId.value == "bonus")
                state.computerSelection.value = bonusOptions.random()
                state.currentState.value = ScreenState.ResultState
                gameBonusLogic()


            when(state.gameState.value){
                "Computer Win" -> state.computerScore.value += 1
                "${state.currentPlayer.value?.username} WIN" -> state.playerScore.value += 1
            }
        }

    }

    private fun gameNormalLogic(){
        when(state.playerSelection.value){
            Options.ROCK.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value = "DRAW"
                    Options.PAPER.name -> state.gameState.value = "Computer Win"
                    Options.SCISSORS.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                }
            }
            Options.PAPER.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value =  "${state.currentPlayer.value?.username} WIN"
                    Options.PAPER.name -> state.gameState.value = "DRAW"
                    Options.SCISSORS.name -> state.gameState.value = "Computer Win"
                }
            }
            Options.SCISSORS.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value = "Computer Win"
                    Options.PAPER.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.SCISSORS.name -> state.gameState.value = "DRAW"
                }
            }
        }
    }

    private fun gameBonusLogic(){
        when(state.playerSelection.value){
            Options.ROCK.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value = "DRAW"
                    Options.PAPER.name -> state.gameState.value = "Computer Win"
                    Options.SCISSORS.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.SPOCK.name -> state.gameState.value = "Computer Win"
                    Options.LIZARD.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                }
            }
            Options.PAPER.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value =  "${state.currentPlayer.value?.username} WIN"
                    Options.PAPER.name -> state.gameState.value = "DRAW"
                    Options.SCISSORS.name -> state.gameState.value = "Computer Win"
                    Options.SPOCK.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.LIZARD.name -> state.gameState.value = "Computer Win"
                }
            }
            Options.SCISSORS.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value = "Computer Win"
                    Options.PAPER.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.SCISSORS.name -> state.gameState.value = "DRAW"
                    Options.SPOCK.name -> state.gameState.value = "Computer Win"
                    Options.LIZARD.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                }
            }
            Options.SPOCK.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.PAPER.name -> state.gameState.value = "Computer Win"
                    Options.SCISSORS.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.SPOCK.name -> state.gameState.value = "DRAW"
                    Options.LIZARD.name -> state.gameState.value = "Computer Win"
                }
            }
            Options.LIZARD.name -> {
                when(state.computerSelection.value){
                    Options.ROCK.name -> state.gameState.value =  "Computer Win"
                    Options.PAPER.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.SCISSORS.name -> state.gameState.value = "Computer Win"
                    Options.SPOCK.name -> state.gameState.value = "${state.currentPlayer.value?.username} WIN"
                    Options.LIZARD.name -> state.gameState.value = "DRAW"
                }
            }
        }
    }

    private fun NextState(){
        viewModelScope.launch {
            if(state.roundsGame.value > state.currentRound.value){
                if(state.gameState.value != "DRAW"){
                    state.currentRound.value += 1
                }
                state.currentState.value = ScreenState.GameState
            }else{
                state.currentState.value = ScreenState.FinalState
            }
        }
    }
    private fun toMenu(){
        sendUiEvent(UiEvent.OnNavigate(Routes.MENU.name))
    }

    fun reset(){
        state.computerScore.value = 0
        state.playerScore.value = 0
        state.currentRound.value = 0
        state.roundsGame.value = 1
    }

    private fun getUser () = viewModelScope.launch (Dispatchers.IO){
        delay(200)
        useCase.getLogged.invoke().let{ user ->
             state.currentPlayer.value = user
        }
    }

    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch { _uiEvent.send(event) }

}