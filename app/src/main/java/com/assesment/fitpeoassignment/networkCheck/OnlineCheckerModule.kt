package com.assesment.fitpeoassignment.networkCheck

import android.content.Context
import android.net.ConnectivityManager
import com.assesment.fitpeoassignment.MainApplication
import dagger.Module
import dagger.Provides

/**
 * Online checker module class for DI
 */
@Module
class OnlineCheckerModule {
    @Provides
    fun onlineChecker(): OnlineChecker {
        val connectivityManager =
            MainApplication.applicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return DefaultOnlineChecker(connectivityManager)
    }
}