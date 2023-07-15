package com.assesment.fitpeoassignment

import android.app.Application
import android.content.Context
import com.assesment.fitpeoassignment.db.PhotoDatabase
import com.assesment.fitpeoassignment.networkCheck.AppComponent
import com.assesment.fitpeoassignment.networkCheck.DaggerAppComponent
import com.assesment.fitpeoassignment.repository.MainRepository

class MainApplication : Application() {

    lateinit var mainRepository: MainRepository

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val retrofitService = RetrofitService.getInstance()
        val photoDatabase = PhotoDatabase.getDatabase(applicationContext())
        mainRepository = MainRepository(retrofitService, photoDatabase)
    }
}