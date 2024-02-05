package com.example.juncookapp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.juncookapp.logic.ConnectivityObserver
import com.example.juncookapp.logic.NetworkConnectivityObserver

@Composable
fun LoginScreen() {
    val context = LocalContext.current

    lateinit var connectivityObserver: ConnectivityObserver

    connectivityObserver = NetworkConnectivityObserver(context)

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "Network status $status",color = MaterialTheme.colorScheme.primary)
    }
}