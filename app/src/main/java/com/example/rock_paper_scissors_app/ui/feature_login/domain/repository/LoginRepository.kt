package com.example.rock_paper_scissors_app.ui.feature_login.domain.repository

import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User

interface LoginRepository {

    fun getUser(name:String): User?

    suspend fun insertUser(user:User)

    fun getUserLogged():User?
}