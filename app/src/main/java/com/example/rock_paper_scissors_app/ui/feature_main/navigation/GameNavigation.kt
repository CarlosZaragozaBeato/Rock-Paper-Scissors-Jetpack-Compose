package com.example.rock_paper_scissors_app.ui.feature_main.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.game.GameScreen
import com.example.rock_paper_scissors_app.ui.feature_game.presentation.screens.menu.MenuScreen
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.access.AccessScreen
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.login.LoginScreen
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.register.RegisterScreen
import com.example.rock_paper_scissors_app.ui.feature_login.presentation.screens.splash.GameSplashScreen

@Composable
fun GameNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SPLASH.name){

        composable(route = Routes.SPLASH.name){
            GameSplashScreen(onPop = {navController.popBackStack()}){ navController.navigate(it) }
        }
        composable(route = Routes.ACCESS.name) {
            AccessScreen(){ navController.navigate(it) }
        }
        composable(route = Routes.LOGIN.name){
            LoginScreen(){ navController.navigate(it.route) }
        }
        composable(route = Routes.REGISTER.name){
            RegisterScreen(){ navController.navigate(it.route) }
        }
        composable(route = Routes.MENU.name){
            MenuScreen(){ navController.navigate(it.route) }
        }
        composable(route = "${Routes.GAME.name}/{type}", arguments = listOf(
            navArgument(name = "type"){
                type = NavType.StringType
            }
        )){
            BackHandler(true){}
            GameScreen(){navController.navigate(it.route)}
        }}
}
