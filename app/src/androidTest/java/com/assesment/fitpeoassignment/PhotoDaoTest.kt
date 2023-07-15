package com.assesment.fitpeoassignment

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.assesment.fitpeoassignment.db.PhotoDao
import com.assesment.fitpeoassignment.db.PhotoDatabase
import com.assesment.fitpeoassignment.model.PhotoDetail
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoDaoTest {
    lateinit var photoDatabase: PhotoDatabase
    private lateinit var photoDao: PhotoDao

    @Before
    fun setup() {
        photoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PhotoDatabase::class.java
        ).allowMainThreadQueries().build()
        photoDao = photoDatabase.photoDao()
    }

    @Test
    fun insertPhoto_expectedSinglePhoto() = runBlocking {
        val photoDetail = PhotoDetail(
            1, 1, "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"
        )
        photoDao.insert(listOf(photoDetail))
        val result = photoDao.getPhotosList()
        assertEquals(1, result.size)
        assertEquals("https://via.placeholder.com/600/92c952", result[0].url)
    }

    @Test
    fun deletePhoto_expectedSinglePhoto() = runBlocking {
        val photoDetail = PhotoDetail(
            1, 1, "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"
        )
        photoDao.insert(listOf(photoDetail))
        photoDao.delete(photoDetail)
        val result = photoDao.getPhotosList()
        assertEquals(0, result.size)
    }

    @After
    fun tear_down() {
        photoDatabase.close()
    }


}