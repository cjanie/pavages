package com.cjanie.pavages

import com.cjanie.pavages.logic.triangles.IsoscelesTriangle
import com.cjanie.pavages.logic.Point
import org.junit.Test

class IsoscelesTriangleTest {

    @Test(expected = AssertionError::class)
    fun `isoceles triangle has two sides of same length`() {
        val A = Point("A", 0.0, 2.0)
        val B = Point("B", -2.0, 0.0)
        val C = Point("C", 1.0, 0.0)
        IsoscelesTriangle(A, B, C)
        }
}