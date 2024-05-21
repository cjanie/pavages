package com.cjanie.pavages

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposablesModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import com.cjanie.pavages.tools.CheckPointInBounds
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DecompositionState(val initialGoldenTriangle: GoldenTriangle) {

    val models = mutableListOf<DecomposablesModel>(

    )

    fun updateModels(newModels: List<DecomposablesModel>) {
        models.clear()
        models.addAll(newModels)
    }

}

class DecompositionStateTest {
    @Test
    fun `point in bounds of a unit decomposition model`() {
        val initialGoldenTriangle = GoldenTriangle()
        val state = DecompositionState(initialGoldenTriangle)
        state.updateModels(listOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
            GoldenTriangle()
        )))
        assertEquals(1, state.models.size)

        state.updateModels(listOf(
                GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(GoldenTriangle()),
                GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(GoldenTriangle())
            ))

        assertEquals(2, state.models.size)

        val container = GoldenTriangle(initialGoldenTriangle.points[0], initialGoldenTriangle.symP1, initialGoldenTriangle.P1)
        val outOfBoundsPoint = Point(x = 1.0, y = 3.0)
        val isInBounds = CheckPointInBounds.isInPointWithinGoldenTriangle(container, outOfBoundsPoint)
        assertFalse(isInBounds)

        val inBoundsPoint = Point(x = 0.1, y = 1.0)
        assertTrue(CheckPointInBounds.isInPointWithinGoldenTriangle(container, inBoundsPoint))
    }
}