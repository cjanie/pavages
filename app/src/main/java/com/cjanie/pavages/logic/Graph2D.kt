package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.createByHeight
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposableModel

class Graph2D(heigth: Double) {

    val goldenTriangle = createByHeight(heigth)

    val symP1 = goldenTriangle.symP1

    fun iterate(iteration: Int, arrange: DecomposableModel): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }
}