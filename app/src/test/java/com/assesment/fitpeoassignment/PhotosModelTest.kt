package com.assesment.fitpeoassignment

import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.utils.ValidationUtil
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotosModelTest {
    lateinit var photoDetail: PhotoDetail

    @Before
    fun setup() {
        photoDetail = PhotoDetail(
            1, 1, "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"
        )
    }

    @After
    fun tearDown() {
        println("End of test")
    }

    @Test
    fun viewModel_Photos() {
        // Verify that the exposed data is correct
        assertEquals(ValidationUtil.isUrlValid(photoDetail), true)
    }

}