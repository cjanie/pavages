package com.cjanie.pavages

import com.cjanie.pavages.logic.EuclideanTiling
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EuclideanTilingTest {

    @Test(expected = AssertionError::class)
    fun `the number of sides of the polygon motive must be under or equal to 7`() {
        EuclideanTiling(8)
    }

    }