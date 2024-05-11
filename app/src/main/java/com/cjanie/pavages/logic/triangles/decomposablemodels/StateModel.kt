package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class StateModel(): DecomposablesModel {

    var goldenTriangle = GoldenTriangle()

    val decomposablesState = mutableListOf<Decomposable>()
    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> = decomposablesState.toTypedArray()
}