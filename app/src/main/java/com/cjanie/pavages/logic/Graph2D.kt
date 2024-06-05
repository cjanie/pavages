package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.CustomModel
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.GoldenTriangle.Companion.createByHeight
class Graph2D(heigth: Double) {

    val goldenTriangle = createByHeight(heigth)

    val goldenGnomon: GoldenGnomon = goldenTriangle.iterate(1, GoldenTriangle.DecomposableModel.TRIANGLE_1_GNOMON_1)[1] as GoldenGnomon

    val symP1 = goldenTriangle.symP1

    fun iterate(iteration: Int, arrange: GoldenTriangle.DecomposableModel): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }

    fun decomposeGnomon(model: GoldenGnomon.DecomposableModel): Array<Decomposable> {
        return goldenGnomon.decompose(model)
    }

    fun iterate(iteration: Int, arrange: CustomModel): Array<Decomposable> {
        return goldenTriangle.iterate(iteration, arrange)
    }
}