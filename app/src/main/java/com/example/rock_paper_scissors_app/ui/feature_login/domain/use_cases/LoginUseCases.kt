package com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases

data class LoginUseCases(
    val insertUser: InsertUseCase,
    val getUser: GetUserUseCase,
    val getLogged:GetLoggedUseCase
)
