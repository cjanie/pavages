package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_bottom_golden_triangle(var goldenTriangle: GoldenTriangle): DecomposablesModel {

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(
            GoldenTriangle(
                goldenTriangle.symP1,
                goldenTriangle.points[1],
                goldenTriangle.P3
            )
        )
    }

    override fun sym(): DecomposablesModel {
        TODO("Not yet implemented")
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }


}