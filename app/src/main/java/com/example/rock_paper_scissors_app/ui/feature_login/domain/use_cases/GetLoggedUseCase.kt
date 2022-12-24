package com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases

import com.example.rock_paper_scissors_app.ui.feature_login.domain.repository.LoginRepository
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import javax.inject.Inject

class GetLoggedUseCase @Inject constructor(
    private val repository: LoginRepository)  {

    operator fun invoke():User?{
        return repository.getUserLogged()
    }
}