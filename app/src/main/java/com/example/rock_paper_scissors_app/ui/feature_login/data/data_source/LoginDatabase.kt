package com.example.rock_paper_scissors_app.ui.feature_login.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User

@Database(
    entities = [User::class],
    version = 1)
abstract class LoginDatabase :RoomDatabase(){
    abstract val loginDao: LoginDao

    companion object {
        const val DATABASE_NAME = "game_db"
    }
}