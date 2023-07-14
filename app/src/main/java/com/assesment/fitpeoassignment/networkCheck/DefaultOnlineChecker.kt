package com.assesment.fitpeoassignment.networkCheck

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Online checker class for checking network connection availability
 */
class DefaultOnlineChecker(private val connectivityManager: ConnectivityManager) : OnlineChecker {
    override val isOnline: Boolean
        get() {
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