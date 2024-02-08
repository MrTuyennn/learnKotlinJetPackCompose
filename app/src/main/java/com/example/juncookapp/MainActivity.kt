package com.example.juncookapp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.juncookapp.logic.CustomNetWork.MyReceiver
import com.example.juncookapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private lateinit var myReceiver: MyReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myReceiver = MyReceiver()
        broadcastIntent()
        setContent {
            AppTheme {
               LoginScreen()
            }
        }
    }

    private fun broadcastIntent() {
        registerReceiver(myReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}



