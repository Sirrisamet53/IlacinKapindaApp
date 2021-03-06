package com.h5190047.ilacinkapinda.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

//Bağlantı varlığını kontrol eden kotlin nesne sınıfıdır
object NetworkUtil {
    fun internetVarMi(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected) {
            return true
        }

        return false
    }
}