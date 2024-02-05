package com.example.juncookapp.logic.monitorimport android.content.Contextimport android.net.ConnectivityManagerimport android.net.Networkimport android.net.NetworkRequestinterface ConnectivityListener {    fun onConnectivityChanged(isConnected: Boolean)}class ConnectivityMonitor(private val context: Context) {    private val connectivityManager =        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager    private val callbackList = mutableListOf<ConnectivityListener>()    private val connectivityCallback = object : ConnectivityManager.NetworkCallback() {        override fun onAvailable(network: Network) {            notifyListeners(true)        }        override fun onLost(network: Network) {            notifyListeners(false)        }    }    fun registerListener(listener: ConnectivityListener) {        callbackList.add(listener)    }    fun unregisterListener(listener: ConnectivityListener) {        callbackList.remove(listener)    }    fun startMonitoring() {        val builder = NetworkRequest.Builder()        connectivityManager.registerNetworkCallback(builder.build(), connectivityCallback)    }    fun stopMonitoring() {        connectivityManager.unregisterNetworkCallback(connectivityCallback)    }    private fun notifyListeners(isConnected: Boolean) {        for (listener in callbackList) {            listener.onConnectivityChanged(isConnected)        }    }}