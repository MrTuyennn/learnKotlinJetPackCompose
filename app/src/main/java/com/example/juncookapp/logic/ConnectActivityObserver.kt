package com.example.juncookapp.logicimport kotlinx.coroutines.flow.Flowinterface ConnectivityObserver {    fun observe(): Flow<Status>    enum class Status {        Available, Unavailable, Losing, Lost    }}