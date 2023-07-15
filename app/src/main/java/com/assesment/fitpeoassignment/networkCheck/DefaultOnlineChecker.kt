package com.assesment.fitpeoassignment.networkCheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.assesment.fitpeoassignment.MainApplication
import dagger.Module
import javax.inject.Inject

/**
 * Online checker class for checking network connection availability
 */

@Module
class DefaultOnlineChecker @Inject constructor() {
    fun isOnline(): Boolean {
        val connectivityManager =
            MainApplication.applicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var result = false
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}