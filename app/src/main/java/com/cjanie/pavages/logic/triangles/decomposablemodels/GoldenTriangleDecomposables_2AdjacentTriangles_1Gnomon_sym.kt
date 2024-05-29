package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(var goldenTriangle: GoldenTriangle) : GoldenTriangleDecomposables_2Triangles_1Gnomon(

), DecomposablesModel {
    override fun goldenTriangles() = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], goldenTriangle.P1),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.P1, goldenTriangle.symP2)
    )

    override fun goldenGnomon() = GoldenGnomon(goldenTriangle.symP2, goldenTriangle.P1, goldenTriangle.points[0])
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> = arrayOf(*goldenTriangles(), goldenGnomon())
    override fun getAdjacentKiteDartModel(): KyteDartModel {
        return KyteDartModel.DART_AT_BOTTOM
    }

    override fun getContigueBaseGoldenTriangleModel(): GoldenTriangleModel {
        return GoldenTriangleModel.PLEIN
    }

    override fun sym(): DecomposablesModel {
        TODO("Not yet implemented")
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun arrange(): GoldenTriangleDecomposables_2Triangles_1Gnomon {
        return GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(goldenTriangle)
    }

    init {
        assert_2Triangles_1Gnomon()
    }
}