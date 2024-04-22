package com.example.juncookapp

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.juncookapp.logic.CustomNetWork.MyReceiver
import com.example.juncookapp.logic.JunCookMedia.AppDatabase
import com.example.juncookapp.logic.JunCookMedia.LoadFilesRepository
import com.example.juncookapp.logic.JunCookMedia.getAllMediaFilesOnDevice
import com.example.juncookapp.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var myReceiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myReceiver = MyReceiver()
        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()

        getAllMediaFilesOnDevice(ComponentActivity());

        // Start a coroutine to call the suspend function
        lifecycleScope.launch {
            // Switch to the IO dispatcher since database operations are usually IO-bound
            withContext(Dispatchers.IO) {
                LoadFilesRepository(ComponentActivity(), appDatabase).loadAllFilesToDatabase()
            }
        }

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



