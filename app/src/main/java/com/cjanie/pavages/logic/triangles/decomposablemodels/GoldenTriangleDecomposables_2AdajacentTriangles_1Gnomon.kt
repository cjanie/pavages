package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2AdajacentTriangles_1Gnomon(
    val goldenTriangle: GoldenTriangle,
): GoldenTriangleDecomposables_2Triangles_1Gnomon(goldenTriangle) {

    override val goldenTriangles = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[2], symP1, goldenTriangle.points[1]),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[2], P2, symP1)
    )

    override val goldenGnomon = GoldenGnomon(P2, goldenTriangle.points[0], symP1)

    init {
        assert_2Triangles_1Gnomon()
    }
}