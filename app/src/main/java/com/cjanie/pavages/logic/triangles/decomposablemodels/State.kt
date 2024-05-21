package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class StateBuilder(
    val goldenTriangle: GoldenTriangle,
    val topGoldenTriangleModel: DecomposablesModel,
    val baseGoldenTriangleModel: DecomposablesModel,
    val symBaseGoldenTrianglemodel: DecomposablesModel
) {

    fun buildStateModel(arrange: Boolean): DecompositionModel {
        val decomposables = arrayOf(
            *topGoldenTriangleModel.decomposables(),
            *symOfTopGoldenTriangleModel(arrange).decomposables(),
            *baseGoldenTriangleModel.decomposables(),
            *symBaseGoldenTrianglemodel.decomposables()
        )
        return DecompositionModel(goldenTriangle, decomposables)
    }

    fun symOfTopGoldenTriangleModel(arrange: Boolean): DecomposablesModel {
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

        return DecompositionModel(symGoldenTriangle_on_P1_symP1_base, symTruncatedBaseDecomposables.toTypedArray())
    }
}