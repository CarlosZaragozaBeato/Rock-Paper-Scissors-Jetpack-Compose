package com.example.rock_paper_scissors_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.rock_paper_scissors_app.ui.feature_main.navigation.GameNavigation
import com.example.rock_paper_scissors_app.ui.theme.Rock_Paper_Scissors_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rock_Paper_Scissors_AppTheme {
                val navController = rememberNavController()
                GameNavigation(navController)
            }
        }
    }
}

