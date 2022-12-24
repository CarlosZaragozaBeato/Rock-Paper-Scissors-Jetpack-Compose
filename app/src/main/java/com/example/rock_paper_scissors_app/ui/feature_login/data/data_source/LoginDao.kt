package com.example.rock_paper_scissors_app.ui.feature_login.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user.User

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user_tb where username == :name")
    fun getUser(name:String):User?

    @Query("SELECT * FROM user_tb where logged == 1")
    fun getUserLogged():User?
}