package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
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
            ))

        val AA = Point(
            x = models[0].goldenTriangle().points[0].x,
            y = models[0].goldenTriangle().symP3.y - (models[0].goldenTriangle().points[0].y - models[0].goldenTriangle().symP1.y)
        )
        val symGoldenTriangle_on_P1_symP1_base = GoldenTriangle(AA, models[0].goldenTriangle().P2, models[0].goldenTriangle().symP2)
        //models[3].updateGoldenTriangle(symGoldenTriangle_on_P1_symP1_base)

    }

    private fun getSymToOppositeToBasePoint(): Point {
        val AA = Point(
            x = models[0].goldenTriangle().points[0].x,
            y = models[0].goldenTriangle().symP3.y - (models[0].goldenTriangle().points[0].y - models[0].goldenTriangle().symP1.y)
        )
        return AA
    }




    fun decomposables(): Array<Decomposable> {

        val decomposables = mutableListOf<Decomposable>()
        for (model in models) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()
    }

    fun symOfTopGoldenTriangleModel(goldenTriangle: GoldenTriangle, arrange: Boolean): DecomposablesModel {
        // define the sym golden triangle AA P1 symP1
        // Height of A symP1 P1 = A.y - symP1.y
        val AA = Point(
            x = goldenTriangle.points[0].x,
            y = goldenTriangle.symP1.y - (goldenTriangle.points[0].y - goldenTriangle.symP1.y)
        )
        val symGoldenTriangle_on_P1_symP1_base = GoldenTriangle(AA, goldenTriangle.P1, goldenTriangle.symP1)
        // get the model adapter for the sym
        val symModelAdapter = if(arrange) GoldenTriangleDecomposables_Dart_atBottom_sym(symGoldenTriangle_on_P1_symP1_base)
        else GoldenTriangleDecomposables_Kite_atBottom_sym(symGoldenTriangle_on_P1_symP1_base)
        // remove pyramidion // TODO a truncated base adapter
        val symTruncatedBaseDecomposables = symModelAdapter.decomposables().toMutableList()
            .toMutableList()
        symTruncatedBaseDecomposables.removeAt(5)

        return StateModel(symGoldenTriangle_on_P1_symP1_base, symTruncatedBaseDecomposables.toTypedArray())
    }
}