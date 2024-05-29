package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Pyramidion(var goldenTriangle: GoldenTriangle, var arrange: Boolean = false): DecomposablesModel {
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*getModel_2Triangles_1Gnomon().decomposables())
    }

    override fun sym(): DecomposablesModel {
        TODO("Not yet implemented")
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    fun getModel_2Triangles_1Gnomon(): GoldenTriangleDecomposables_2Triangles_1Gnomon {
        val model_2Triangles_1Gnomon =
            if (arrange) GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
            else GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
        return model_2Triangles_1Gnomon
    }

    fun getAdjacentModel_kite_dart(): KyteDartModel {
        return getModel_2Triangles_1Gnomon().getAdjacentKiteDartModel()
    }

    fun getContigueBaseGoldenTriangle(): GoldenTriangleModel {
        return getModel_2Triangles_1Gnomon().getContigueBaseGoldenTriangleModel()
    }

}