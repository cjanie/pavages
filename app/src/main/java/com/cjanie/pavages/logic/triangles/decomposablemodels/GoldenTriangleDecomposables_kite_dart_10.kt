package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_kite_dart_10(goldenTriangle: GoldenTriangle, val kite: Array<Decomposable>, val dart: Array<Decomposable>) : DecomposablesModel {
    override fun goldenTriangle(): GoldenTriangle {
        TODO("Not yet implemented")
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        TODO("Not yet implemented")
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*kite, *dart)
    }
}