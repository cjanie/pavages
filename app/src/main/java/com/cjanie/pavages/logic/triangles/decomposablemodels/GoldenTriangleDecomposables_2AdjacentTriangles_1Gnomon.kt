package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
    val goldenTriangle: GoldenTriangle,
): GoldenTriangleDecomposables_2Triangles_1Gnomon(goldenTriangle), DecomposablesModel {

    override val goldenTriangles = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.symP1, goldenTriangle.points[1]),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P2, goldenTriangle.symP1)
    )

    override val goldenGnomon = GoldenGnomon(goldenTriangle.P2, goldenTriangle.points[0], goldenTriangle.symP1)

    override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, goldenGnomon)

    init {
        assert_2Triangles_1Gnomon()
    }
}