package com.assesment.fitpeoassignment.utils

import com.assesment.fitpeoassignment.model.PhotoDetail

object ValidationUtil {


    /**
     * To check if url is not empty and valid before loading it to Img View.
     * Wrote a unit test case for the same in PhotoModelTest class
     */
    fun isUrlValid(photoDetail: PhotoDetail): Boolean {
        if (photoDetail.url!!.isNotEmpty()) {
            return true
        }
        return false
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length > 6 && password.length < 15
    }

}