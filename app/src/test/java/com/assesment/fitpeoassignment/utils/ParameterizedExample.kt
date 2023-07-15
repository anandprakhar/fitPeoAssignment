package com.assesment.fitpeoassignment.utils

import com.assesment.fitpeoassignment.model.PhotoDetail
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedExample(
    private val photoDetail: PhotoDetail,
    private val expectedValue: Boolean
) {

    @Test
    fun test() {
        val result = ValidationUtil.isUrlValid(photoDetail)
        assertEquals(expectedValue, result)
    }

    companion object {


        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is url and result is {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(
                    PhotoDetail(
                        1,
                        1,
                        "accusamus beatae ad facilis cum similique qui sunt",
                        "https://via.placeholder.com/600/92c952",
                        "https://via.placeholder.com/150/92c952"
                    ), true
                ),
                arrayOf(
                    PhotoDetail(
                        1, 1, "accusamus beatae ad facilis cum similique qui sunt",
                        "", "https://via.placeholder.com/150/92c952"
                    ), false
                )

            )
        }
    }
}