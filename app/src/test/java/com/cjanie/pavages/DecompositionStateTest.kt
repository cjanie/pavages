package com.cjanie.pavages

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.DecompositionState
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposablesModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2Triangles_1Gnomon
import com.cjanie.pavages.tools.CheckPointInBounds
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

        assertEquals(2, state.models.size)
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
}