package com.assesment.fitpeoassignment.utils

import com.assesment.fitpeoassignment.model.PhotoDetail
import junit.framework.TestCase
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ValidationUtilTest {
    private lateinit var photoDetail: PhotoDetail
    lateinit var password: String
    @Before
    fun setUp() {
        photoDetail = PhotoDetail(
            1, 1, "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"
        )
        password= "qwertyu"
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_url_is_not_empty(){
        // Verify that the exposed data is correct
        val result= ValidationUtil.isUrlValid(photoDetail)
       assertEquals(true, result)
    }

    @Test
    fun isValidPassword() {
        val isValidPass=ValidationUtil.isValidPassword(password)
        assertEquals(true,isValidPass)
    }
}