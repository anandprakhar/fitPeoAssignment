package com.assesment.fitpeoassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assesment.fitpeoassignment.repository.MainRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}