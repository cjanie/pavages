package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class StateModel(var goldenTriangle: GoldenTriangle, var decomposables: Array<Decomposable> = emptyArray()): DecomposablesModel {
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }
    fun updateDecomposables(decomposables: Array<Decomposable>) {
        this.decomposables = decomposables
    }

    override fun decomposables(): Array<Decomposable> = decomposables
}