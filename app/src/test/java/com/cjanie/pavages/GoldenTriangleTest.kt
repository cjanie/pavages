package com.cjanie.pavages

import com.cjanie.pavages.logic.Cercle
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.PentagonBuilder
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class GoldenTriangleTest {

    val goldenTriangle = GoldenTriangle()

    @Test(expected = AssertionError::class)
    fun `duplicated side length is in the golden ratio phi to the base side`() {
        val A = Point("A", 0.0, 2.0)
        val B = Point("B", -2.0, 0.0)
        val C = Point("C", 2.0, 0.0)
        GoldenTriangle(A, B, C)
    }

    @Test
    fun `golden triangle`() {
        val pentagon = PentagonBuilder(
            Cercle(Point("O", 0.0, 0.0), 2.0)
        ).build()
        val triangle = pentagon.goldenTriangle
        assertEquals(Number.GOLDEN_NUMBER_PHI.toFloat(), (triangle.duplicatedSideLength() / triangle.baseSideLength()).toFloat(), 0.0f)
    }

    @Test
    fun `golden ratio checked`() {
        GoldenTriangle()
    }

    @Test
    fun `decompose 1st iteration returns 2 golden triangles and 1 golden gnomon`() {
        val goldenTriangle = GoldenTriangle()
        val decomposables = goldenTriangle.iterate(1)
        assertEquals(3, decomposables.size)

        val goldenTriangles = decomposables.filter { it is GoldenTriangle }
        assertEquals(2, goldenTriangles.size)

        val gnomons = decomposables.filter { it is GoldenGnomon }
        assertEquals(1, gnomons.size)
    }

    @Test
    fun `decompose 1st iteration another arrangement`() {

        val decomposables = goldenTriangle.iterate(1, true)
        assertEquals(3, decomposables.size)

        val goldenTriangles = decomposables.filter { it is GoldenTriangle }
        assertEquals(2, goldenTriangles.size)

        val gnomons = decomposables.filter { it is GoldenGnomon }
        assertEquals(1, gnomons.size)
    }

    @Test
    fun `decompose 2nd iteration return 5 golden triangles and 3 golden gnomons`() {
        val decomposables = goldenTriangle.iterate(2)
        assertEquals(8, decomposables.size)

        val goldenTriangles = decomposables.filter { it is GoldenTriangle }
        assertEquals(5, goldenTriangles.size)

        val gnomons = decomposables.filter { it is GoldenGnomon }
        assertEquals(3, gnomons.size)
    }

    @Test
    fun `decompose 2nd other arrangement`() {
        val decomposables = goldenTriangle.iterate(2, true)
        assertEquals(8, decomposables.size)

        val goldenTriangles = decomposables.filter { it is GoldenTriangle }
        assertEquals(5, goldenTriangles.size)

        val gnomons = decomposables.filter { it is GoldenGnomon }
        assertEquals(3, gnomons.size)
    }
}