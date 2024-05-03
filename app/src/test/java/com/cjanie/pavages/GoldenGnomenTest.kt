package com.cjanie.pavages

import com.cjanie.pavages.logic.NumberConstants
import com.cjanie.pavages.logic.Pentagon
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import org.junit.Assert.assertEquals
import org.junit.Test

class GoldenGnomenTest {

    @Test(expected = AssertionError::class)
    fun `duplicated side is in the one on phi ratio to the base side`() {
        val A = Point("A", 0.0, 2.0)
        val B = Point("B", -2.0, 0.0)
        val C = Point("C", 2.0, 0.0)
        GoldenGnomon(A, B, C)
    }

    @Test
    fun `golden gnomon`() {
        val pentagon = Pentagon(2.0)
        val gnomon = GoldenGnomon.createGoldenGnomons(pentagon)[0]
        assertEquals((1 / NumberConstants.GOLDEN_NUMBER_PHI).toFloat(), (gnomon.duplicatedSideLength() / gnomon.baseSideLength()).toFloat(), 0.0f)
    }
}