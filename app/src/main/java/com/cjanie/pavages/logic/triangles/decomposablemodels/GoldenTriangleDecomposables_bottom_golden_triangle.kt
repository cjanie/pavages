package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.enums.Position
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_bottom_golden_triangle(var goldenTriangle: GoldenTriangle, var models: Array<DecomposablesModel> = emptyArray(), var position: Position = Position.START): DecomposablesModel {

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        if(models.isEmpty())
            return arrayOf(
                getBottomTriangle()
            )
        else {
            val decomposables = mutableListOf<Decomposable>()
            for (model in models) {
                if(position == Position.START) model.updateGoldenTriangle(getBottomTriangle())
                else model.updateGoldenTriangle(getSymBottomTriangle())
                decomposables.addAll(model.decomposables())
            }
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

    private fun getSymBottomTriangle(): GoldenTriangle{
        return GoldenTriangle(goldenTriangle.P1, goldenTriangle.symP3, goldenTriangle.points[2])
    }


}