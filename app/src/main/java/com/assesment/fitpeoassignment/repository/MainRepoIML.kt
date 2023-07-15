package com.assesment.fitpeoassignment.repository

import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.utils.NetworkResult

interface MainRepoIML {
    suspend fun getAllPhotos(): NetworkResult<List<PhotoDetail>>
}