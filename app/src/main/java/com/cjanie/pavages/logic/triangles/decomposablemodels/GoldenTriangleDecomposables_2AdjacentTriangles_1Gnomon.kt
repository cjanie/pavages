package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
    var goldenTriangle: GoldenTriangle
): GoldenTriangleDecomposables_2Triangles_1Gnomon(), DecomposablesModel {

    override fun goldenTriangles() = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.symP1, goldenTriangle.points[1]),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P2, goldenTriangle.symP1)
    )

    override fun goldenGnomon() = GoldenGnomon(goldenTriangle.P2, goldenTriangle.points[0], goldenTriangle.symP1)
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> = arrayOf(*goldenTriangles(), goldenGnomon())

    fun sym(): GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym {
        return GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(goldenTriangle)
    }
    init {
        assert_2Triangles_1Gnomon()
    }
}