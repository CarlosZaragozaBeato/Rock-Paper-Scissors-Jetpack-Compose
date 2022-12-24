package com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases

import com.example.rock_paper_scissors_app.ui.feature_login.domain.repository.LoginRepository
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(name:String): User?{
        return repository.getUser(name)
    }
}