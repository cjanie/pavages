package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_kite_dart_10(goldenTriangle: GoldenTriangle, val kite: Array<Decomposable>, val dart: Array<Decomposable>) : DecomposablesModel {

    var container = goldenTriangle
    override fun goldenTriangle(): GoldenTriangle {
        return container
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        container = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*kite, *dart)
    }
}