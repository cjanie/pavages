package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.create
import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.createByHeight

class Graph2D(heigth: Double) {

    val goldenTriangle = createByHeight(heigth)

    val symP1 = goldenTriangle.symP1
    val P1 = goldenTriangle.P1

    fun iterate(iteration: Int, arrange: Boolean): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }
}