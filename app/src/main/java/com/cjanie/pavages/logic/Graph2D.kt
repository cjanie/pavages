package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Graph2D {

    val goldenTriangle = GoldenTriangle.create(baseLength = 200.0)

    fun iterate(iteration: Int): Array<Decomposable> {
        return goldenTriangle.iterate(iteration)
    }

}