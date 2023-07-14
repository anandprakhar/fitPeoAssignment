package com.assesment.fitpeoassignment.repository

import com.assesment.fitpeoassignment.RetrofitService
import com.assesment.fitpeoassignment.model.PhotoDetail

/**
 * Repo class
 */
class MainRepository(private val retrofitService: RetrofitService) : MainRepoIML {
    override suspend fun getAllPhotos(): List<PhotoDetail> = retrofitService.getPhotos()
}