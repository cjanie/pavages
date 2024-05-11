package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_RosaceUnit(
    var goldenTriangle: GoldenTriangle,
    val models: Array<DecomposablesModel>,
    val arrange: Boolean
) {
    fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    fun pushUp() {

        models[1].updateGoldenTriangle(GoldenTriangle(
            models[0].goldenTriangle().symP1,
            models[0].goldenTriangle().points[1],
            models[0].goldenTriangle().P3
            ))
        models[2].updateGoldenTriangle(GoldenTriangle(
            models[0].goldenTriangle().P1,
            models[0].goldenTriangle().symP3,
            models[0].goldenTriangle().points[2]
        ))
        models[0].updateGoldenTriangle(
            GoldenTriangle(
            goldenTriangle.points[0],
                models[1].goldenTriangle().points[0],
                models[2].goldenTriangle().points[0]
        )
        )

    }
    fun decomposables(): Array<Decomposable> {
        //build()
        val decomposables = mutableListOf<Decomposable>()
        for (model in models) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()
    }
}