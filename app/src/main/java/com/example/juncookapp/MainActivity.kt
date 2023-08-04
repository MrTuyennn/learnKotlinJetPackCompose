package com.example.juncookapp

import android.content.res.Configuration
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.juncookapp.Utils.AppTheme
import com.example.juncookapp.ui.theme.JuncookappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
               LoginScreen()
            }
        }
    }
}

