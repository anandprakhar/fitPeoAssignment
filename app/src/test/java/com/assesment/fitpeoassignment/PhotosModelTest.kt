package com.assesment.fitpeoassignment

import com.assesment.fitpeoassignment.model.PhotoDetail
import com.assesment.fitpeoassignment.utils.ValidationUtil
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotosModelTest {

    @Test
    fun viewModel_Photos() {
        // Given a VM using fake data
        val photoDetail = PhotoDetail(
            1, 1, "accusamus beatae ad facilis cum similique qui sunt",
            "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"
        )
        // Verify that the exposed data is correct
        assertEquals(ValidationUtil.isUrlValid(photoDetail), true)
    }

}