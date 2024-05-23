package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Pyramidion(var goldenTriangle: GoldenTriangle, var arrange: Boolean = false): DecomposablesModel {
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*model_2Triangles_1Gnomon().decomposables())
    }

    fun model_2Triangles_1Gnomon(): GoldenTriangleDecomposables_2Triangles_1Gnomon {
        val model_2Triangles_1Gnomon =
            if (arrange) GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
            else GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
        return model_2Triangles_1Gnomon
    }

}