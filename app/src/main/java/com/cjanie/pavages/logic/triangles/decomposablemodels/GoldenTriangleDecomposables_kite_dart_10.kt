package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_kite_dart_10(
    goldenTriangle: GoldenTriangle,
    val baseGoldenTriangleModel: GoldenTriangleDecomposables_2Triangles_1Gnomon,
    val symTopGoldenTriangleModel: GoldenTriangleDecomposables_2Triangles_1Gnomon,
    val symKiteDartModel: GoldenTriangleDecomposables_Kite_Dart,
    val symBottomGoldenTriangleModel: DecomposablesModel
) : GoldenTriangleDecomposables_Kite_Dart(goldenTriangle), DecomposablesModel {

    var container = goldenTriangle
    override fun goldenTriangle(): GoldenTriangle {
        return container
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        container = goldenTriangle
    }

    override fun kite(): Array<Decomposable> {
        return arrayOf(
            *symKiteDartModel.decomposables(),
            symTopGoldenTriangleModel.goldenTriangles()[0],
            *symBottomGoldenTriangleModel.decomposables()
        )
    }

    override fun dart(): Array<Decomposable> {
        return arrayOf(
            *baseGoldenTriangleModel.decomposables(),
            symTopGoldenTriangleModel.goldenGnomon()
        )
    }

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        TODO("Not yet implemented")
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*kite(), *dart())
    }
}