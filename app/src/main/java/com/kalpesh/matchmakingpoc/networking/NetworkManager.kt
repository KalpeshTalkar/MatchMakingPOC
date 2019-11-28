package com.kalpesh.matchmakingpoc.networking

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.kalpesh.matchmakingpoc.App

fun isConnectedToInternet(): Boolean {
    val connectivityManager = App.sharedContext()
        .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
}