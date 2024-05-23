package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_bottom_golden_triangle(var goldenTriangle: GoldenTriangle, var models: Array<DecomposablesModel> = emptyArray()): DecomposablesModel {

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
        for (model in models) {
            updateGoldenTriangle(this.goldenTriangle)
        }
    }

    override fun decomposables(): Array<Decomposable> {
        if(models.isEmpty())
            return arrayOf(
                getBottomTriangle()
            )
        else {
            val decomposables = mutableListOf<Decomposable>()
            for (model in models) decomposables.addAll(model.decomposables())
            return decomposables.toTypedArray()
        }
    }

    override fun sym(): DecomposablesModel {
        return GoldenTriangleDecomposables_bottom_golden_triangle_sym(goldenTriangle)
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    private fun getBottomTriangle(): GoldenTriangle {
        return GoldenTriangle(
            goldenTriangle.symP1,
            goldenTriangle.points[1],
            goldenTriangle.P3
        )
    }


}