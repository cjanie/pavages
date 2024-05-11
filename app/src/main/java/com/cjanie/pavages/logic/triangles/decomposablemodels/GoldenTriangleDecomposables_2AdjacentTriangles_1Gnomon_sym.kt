package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(var goldenTriangle: GoldenTriangle) : GoldenTriangleDecomposables_2Triangles_1Gnomon(

) {
    override fun goldenTriangles() = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], goldenTriangle.P1),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.P1, goldenTriangle.symP2)
    )

    override fun goldenGnomon() = GoldenGnomon(goldenTriangle.symP2, goldenTriangle.P1, goldenTriangle.points[0])
    override fun decomposables(): Array<Decomposable> = arrayOf(*goldenTriangles(), goldenGnomon())

    init {
        assert_2Triangles_1Gnomon()
    }
}