package com.assesment.fitpeoassignment.repository

import com.assesment.fitpeoassignment.RetrofitService
import com.assesment.fitpeoassignment.db.PhotoDatabase
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.networkCheck.DaggerAppComponent
import com.assesment.fitpeoassignment.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Repo class
 */

class MainRepository(
    private val retrofitService: RetrofitService, private val photoDatabase: PhotoDatabase
) : MainRepoIML {

//    @Inject
//    var onlineChecker = OnlineCheckerModule().onlineChecker()

    override suspend fun getAllPhotos(): NetworkResult<List<PhotoDetail>> {
        val component = DaggerAppComponent.builder().build().getDefaultOnlineChecker()
        if (!component.isOnline()) {
            var responseBody: List<PhotoDetail>?
            //fetching data through async
            val job = CoroutineScope(Dispatchers.IO).async {
                responseBody = photoDatabase.photoDao().getPhotosList()
                return@async responseBody
            }
            return if (job.await()!!.isEmpty()) {
                NetworkResult.Error(message = "No List found!")
            } else {
                NetworkResult.Success(job.await()!!)
            }
        } else {
            var response: Response<List<PhotoDetail>>? = null
            val job = CoroutineScope(Dispatchers.IO).async {
                response = retrofitService.getPhotos()
                return@async response
            }
            return if (job.await()!!.isSuccessful) {
                val responseBody = job.await()!!.body()
                if (responseBody != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        photoDatabase.photoDao().insert(job.await()!!.body()!!)
                    }
                    NetworkResult.Success(responseBody)
                } else {
                    NetworkResult.Error(message = "Something went wrong!")
                }
            } else {
                NetworkResult.Error(message = "Something went wrong!")
            }

        }

    }
}