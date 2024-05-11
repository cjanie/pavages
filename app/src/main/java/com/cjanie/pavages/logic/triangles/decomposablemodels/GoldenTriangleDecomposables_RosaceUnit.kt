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
        this.goldenTriangle = GoldenTriangle(
            this.goldenTriangle.points[0],
            models[0].goldenTriangle().symP2,
            models[0].goldenTriangle().P2
        )
        // Adapt the model scale
        models[0].updateGoldenTriangle(
            GoldenTriangle(
                this.goldenTriangle.points[0],
                models[0].goldenTriangle().symP1,
                models[0].goldenTriangle().P1
            )
        )

    }
    fun build() {
        val model = StateBuilder(
            goldenTriangle,
            models[0],
            models[1],
            models[2]
        )
        model.buildStateModel(arrange)

        for(model in models) {
            model.updateGoldenTriangle(
                (GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1))
            )

        }

    }



    fun decomposables(): Array<Decomposable> {
        build()
        val decomposables = mutableListOf<Decomposable>()
        for (model in models) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()
    }
}