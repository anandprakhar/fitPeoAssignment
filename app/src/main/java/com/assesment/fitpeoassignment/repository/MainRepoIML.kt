package com.assesment.fitpeoassignment.repository

import com.assesment.fitpeoassignment.model.PhotoDetail

interface MainRepoIML {
    suspend fun getAllPhotos(): List<PhotoDetail>
}