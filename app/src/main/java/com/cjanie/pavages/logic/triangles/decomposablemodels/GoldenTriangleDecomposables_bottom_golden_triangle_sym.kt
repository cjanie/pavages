package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.enums.Position
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_bottom_golden_triangle_sym(var goldenTriangle: GoldenTriangle): DecomposablesModel {
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(
            GoldenTriangle(
                this.goldenTriangle.P1,
                this.goldenTriangle.symP3,
                this.goldenTriangle.points[2]
            )
        )
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun sym(): DecomposablesModel {
        return GoldenTriangleDecomposables_bottom_golden_triangle(goldenTriangle)
    }
}