package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposablesModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecompositionModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2Triangles_1Gnomon
import com.cjanie.pavages.tools.CheckPointInBounds

class DecompositionState {

    private val decomposables = mutableListOf<Decomposable>()

    fun updateDecomposables(decomposables: Array<Decomposable>) {
        this.decomposables.clear()
        this.decomposables.addAll(decomposables)
    }

    fun decomposables(): Array<Decomposable> {
        return this.decomposables.toTypedArray()
    }

}
