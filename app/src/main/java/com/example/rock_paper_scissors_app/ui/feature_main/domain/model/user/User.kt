package com.example.rock_paper_scissors_app.ui.feature_main.domain.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tb")
data class User(
    @PrimaryKey
    val id:Int? = null,
    val username:String,
    val logged:Int=0
)
