package com.cjanie.pavages

import com.cjanie.pavages.logic.Cercle
import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.PentagonBuilder
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.IsoscelesTriangle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GoldenTriangleTest {

    @Test(expected = AssertionError::class)
    fun `duplicated side length is in the golden ratio phi to the base side`() {
        val A = Point("A", 0.0, 2.0)
        val B = Point("B", -2.0, 0.0)
        val C = Point("C", 2.0, 0.0)
        GoldenTriangle(A, B, C)
    }

    @Test
    fun `golden ratio checked`() {
        GoldenTriangle()
    }
}