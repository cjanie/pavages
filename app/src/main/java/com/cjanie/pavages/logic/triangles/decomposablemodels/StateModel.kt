package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable

class StateModel(): DecomposablesModel {

    val decomposablesState = mutableListOf<Decomposable>()
    override val decomposables: Array<Decomposable>
        get() = decomposablesState.toTypedArray()
}