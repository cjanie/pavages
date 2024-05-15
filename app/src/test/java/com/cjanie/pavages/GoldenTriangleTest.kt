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
        // SUT
        val decomposables = GoldenTriangle().iterate(1)

        assertDecompositionRatio(
            expectedTotal = 3,
            expectedGoldenTrianglesCount = 2,
            expectedGoldenGnomonsCount = 1,
            decomposables
            )
    }

    @Test fun `decompose 1st iteration non adjacent golden triangles arrangement`() {
        // SUT
        val decomposables = GoldenTriangle().iterate(1)

        assertFalse(isAdjacentGoldenTriangles(decomposables))
    }

    @Test
    fun `decompose 1st iteration adjacent golden triangles arrangement`() {

        val decomposables = GoldenTriangle().iterate(1, true)

        assertDecompositionRatio(
            expectedTotal = 3,
            expectedGoldenTrianglesCount = 2,
            expectedGoldenGnomonsCount = 1,
            decomposables
        )

        assertTrue(isAdjacentGoldenTriangles(decomposables))
    }

    @Test
    fun `decompose 2nd iteration return 5 golden triangles and 3 golden gnomons`() {

        val decomposables = GoldenTriangle().iterate(2)

        assertDecompositionRatio(
            expectedTotal = 8,
            expectedGoldenTrianglesCount = 5,
            expectedGoldenGnomonsCount = 3,
            decomposables
        )
    }

    @Test
    fun `decompose 2nd iteration non adjacent golden triangles arrangement`() {

        val decomposables = GoldenTriangle().iterate(2)

        assertFalse(isAdjacentGoldenTriangles(decomposables))
    }

    @Test
    fun `decompose 2nd adjacent golden triangles arrangement`() {

        val decomposables = GoldenTriangle().iterate(2, true)

        assertDecompositionRatio(
            expectedTotal = 8,
            expectedGoldenTrianglesCount = 5,
            expectedGoldenGnomonsCount = 3,
            decomposables
        )

        assertTrue(isAdjacentGoldenTriangles(decomposables))
    }

    @Test
    fun `decompose third iteration returns 13 golden triangles and 8 golden gnomons`() {

        val decomposables = GoldenTriangle().iterate(3, false)

        assertDecompositionRatio(
            expectedTotal = 21,
            expectedGoldenTrianglesCount = 13,
            expectedGoldenGnomonsCount = 8,
            decomposables
        )
    }

    @Test
    fun `decompose third iteration 5 adjacent golden triangles in P1 arrangement`() {
        val goldenTriangle = GoldenTriangle()

        val decomposables = goldenTriangle.iterate(3, false)

        assertDecompositionRatio(
            expectedTotal = 21,
            expectedGoldenTrianglesCount = 13,
            expectedGoldenGnomonsCount = 8,
            decomposables
        )

        val goldenTriangles: Array<GoldenTriangle> = (decomposables.filter { it is GoldenTriangle } as List<GoldenTriangle>).toTypedArray()
        val adjacentsInP1 = goldenTriangles.filter { it.points[0] == goldenTriangle.P1 }

        assertEquals(5, adjacentsInP1.size)

        val kiteInB = goldenTriangles.filter { it.points[0] == goldenTriangle.points[1] }
        assertEquals(2, kiteInB.size)

        val topNonAdjacent = goldenTriangles.filter {it.points[0] == goldenTriangle.P2}
        assertEquals(0, topNonAdjacent.size)
    }

    @Test
    fun `decompose third iteration 5 adjacent golden gnomons in P1 arrangement`() {
        val goldenTriangle = GoldenTriangle()

        val decomposables = goldenTriangle.iterate(3, true)

        assertDecompositionRatio(
            expectedTotal = 21,
            expectedGoldenTrianglesCount = 13,
            expectedGoldenGnomonsCount = 8,
            decomposables
        )

        val goldenGnomons: Array<GoldenGnomon> = (decomposables.filter { it is GoldenGnomon } as List<GoldenGnomon>).toTypedArray()
        val adjacentsInP1ByBasePoint1 = goldenGnomons.filter { it.points[1] == goldenTriangle.P1 }
        val adjacentsInP1ByBasePoint2 = goldenGnomons.filter { it.points[2] == goldenTriangle.P1 }
        val adjacents = mutableListOf<GoldenGnomon>()
        adjacents.addAll(adjacentsInP1ByBasePoint1)
        adjacents.addAll(adjacentsInP1ByBasePoint2)
        assertEquals(5, adjacents.size)

        val goldenTriangles: Array<GoldenTriangle> = (decomposables.filter { it is GoldenTriangle } as List<GoldenTriangle>).toTypedArray()
        val kiteInB = goldenTriangles.filter { it.points[0] == goldenTriangle.points[1] }
        assertEquals(2, kiteInB.size)

        val topNonAdjacent = goldenTriangles.filter {it.points[0] == goldenTriangle.P2}
        assertEquals(0, topNonAdjacent.size)
    }



    private fun assertDecompositionRatio(
        expectedTotal: Int,
        expectedGoldenTrianglesCount: Int,
        expectedGoldenGnomonsCount: Int,
        actualDecomposables: Array<Decomposable>) {
        assertEquals(expectedTotal, actualDecomposables.size)

        val goldenTriangles = actualDecomposables.filter { it is GoldenTriangle }
        assertEquals(expectedGoldenTrianglesCount, goldenTriangles.size)

        val goldenGnomons = actualDecomposables.filter { it is GoldenGnomon }
        assertEquals(expectedGoldenGnomonsCount, goldenGnomons.size)
    }

    private fun isAdjacentGoldenTriangles(decomposables: Array<Decomposable>) : Boolean {
        val goldenTriangles = (decomposables.filter { it is GoldenTriangle } as List<IsoscelesTriangle>).toTypedArray()
        return isAdjacent(goldenTriangles)

    }

    private fun isAdjacent(isocèles: Array<IsoscelesTriangle>): Boolean {
        val oppositeToBasePoint = isocèles[0].points[0]
        return isocèles.size == isocèles.filter { it.points[0] == oppositeToBasePoint}.size
    }

}