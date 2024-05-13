package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.create

class Graph2D(heigth: Double) {

    val goldenTriangle = create(baseLength = 600.0)

    fun iterate(iteration: Int, arrange: Boolean): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }
}