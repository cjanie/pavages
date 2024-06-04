package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.CustomModel
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.createByHeight
class Graph2D(heigth: Double) {

    val goldenTriangle = createByHeight(heigth)

    val symP1 = goldenTriangle.symP1

    fun iterate(iteration: Int, arrange: GoldenTriangle.DecomposableModel): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }

    fun iterate(iteration: Int, arrange: CustomModel): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }
}