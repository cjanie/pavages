package com.cjanie.pavages

import com.cjanie.pavages.logic.Cercle
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.PentagonBuilder
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

}