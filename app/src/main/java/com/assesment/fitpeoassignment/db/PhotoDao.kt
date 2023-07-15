package com.assesment.fitpeoassignment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.assesment.fitpeoassignment.model.PhotoDetail

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photoDetail: List<PhotoDetail>)

    @Update
    suspend fun update(photoDetail: List<PhotoDetail>)

    @Query("SELECT * from Photo")
    fun getPhotosList(): List<PhotoDetail>
}