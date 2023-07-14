package com.assesment.fitpeoassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.networkCheck.OnlineCheckerModule
import com.assesment.fitpeoassignment.repository.MainRepoIML
import com.assesment.fitpeoassignment.repository.MainRepository
import com.assesment.fitpeoassignment.utils.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(private val mainRepository: MainRepoIML) : ViewModel() {

    val photoList = MutableLiveData<List<PhotoDetail>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    @Inject
    var onlineChecker = OnlineCheckerModule().onlineChecker()


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception Handled : ${throwable.localizedMessage}")
    }
    private val _networkState = MutableLiveData<NetworkState>()
    val returnNetworkState: LiveData<NetworkState>
        get() = _networkState

    fun getPhotosList() {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            if (!onlineChecker.isOnline) {
                _networkState.postValue(NetworkState.ERROR)
                loading.postValue(false)
            } else {
                val response = mainRepository.getAllPhotos()
                if (response.isNotEmpty()) {
                    photoList.postValue(response)
                    loading.postValue(false)
                } else {
                    onError("Error : error")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}