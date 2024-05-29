package com.cjanie.pavages

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.DecompositionState
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
class DecompositionStateTest {
    @Test
    fun `decomposition state has models`() {
        val state = DecompositionState()
        state.updateModels(listOf(
            GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(GoldenTriangle()),
            GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(GoldenTriangle())
        ))

        assertEquals(2, state.getModels().size)
    }

    @Test
    fun `point in bounds of a model returns the model`() {
        val initialGoldenTriangle = GoldenTriangle()
        val state = DecompositionState()

        val container = GoldenTriangle(initialGoldenTriangle.points[0], initialGoldenTriangle.symP1, initialGoldenTriangle.P1)
        state.updateModels(listOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(container)))

        val inBoundsPoint = Point(x = 0.1, y = 1.0)
        val model = state.arrangeSelectedModel(inBoundsPoint)
        assertNotNull(model)
    }

    @Test
    fun `point out of bounds`() {
        val initialGoldenTriangle = GoldenTriangle()
        val state = DecompositionState()
        val container = GoldenTriangle(initialGoldenTriangle.points[0], initialGoldenTriangle.symP1, initialGoldenTriangle.P1)
        state.updateModels(listOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(container)))
        val outOfBoundsPoint = Point(x = 1.0, y = 3.0)
        val model = state.arrangeSelectedModel(outOfBoundsPoint)
        assertNull(model)
    }

    @Test
    fun `arrange selected model`() {
        val initialGoldenTriangle = GoldenTriangle()
        val state = DecompositionState()

        val container = GoldenTriangle(initialGoldenTriangle.points[0], initialGoldenTriangle.symP1, initialGoldenTriangle.P1)

        state.updateModels(listOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(container)))

        val inBoundsPoint = Point(x = 0.1, y = 1.0)
        val model = state.arrangeSelectedModel(inBoundsPoint)
        assertTrue(model is GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon)
    }

    @Test
    fun rosaceStarArrangement() {
        val goldenTriangle = GoldenTriangle()
        val decomposables = goldenTriangle.iterate(3, true)
        val gnomons: List<GoldenGnomon> = decomposables.filter { it is GoldenGnomon } as List<GoldenGnomon>
        val star = mutableListOf<GoldenGnomon>()
        for (gnomon in gnomons) {
            for (point in gnomon.points) {
                if (point == goldenTriangle.P1) {
                    star.add(gnomon)
                }
            }
        }
        assertEquals(5, star.size)
    }

    @Test
    fun rosaceSunArrangement() {
        val goldenTriangle = GoldenTriangle()
        val decomposables = goldenTriangle.iterate(3, false)
        val goldenTriangles: List<GoldenTriangle> = decomposables.filter { it is GoldenTriangle } as List<GoldenTriangle>
        val sun = mutableListOf<GoldenTriangle>()
        for (triangle in goldenTriangles) {
            if (triangle.points[0] == goldenTriangle.P1) {
                sun.add(triangle)
            }
        }
        assertEquals(5, sun.size)
    }
}