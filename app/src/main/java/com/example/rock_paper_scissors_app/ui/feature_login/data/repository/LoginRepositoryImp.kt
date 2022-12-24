package com.example.rock_paper_scissors_app.ui.feature_login.data.repository

import com.example.rock_paper_scissors_app.ui.feature_login.data.data_source.LoginDao
import com.example.rock_paper_scissors_app.ui.feature_login.domain.repository.LoginRepository
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val dao:LoginDao
):LoginRepository{
    override fun getUser(name: String): User? {
       return dao.getUser(name)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override fun getUserLogged(): User? {
        return dao.getUserLogged()
    }
}