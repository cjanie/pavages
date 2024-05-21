package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Dart_atBottom(
    override var goldenTriangle: GoldenTriangle
):
    GoldenTriangleDecomposables_Kite_Dart(goldenTriangle) {
    override fun kite(): Array<Decomposable> = arrayOf(
        // symP1 P5, P1
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P5, goldenTriangle.P1),
        // symP1 P3 P5
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P3, goldenTriangle.P5)
    )

    override fun dart(): Array<Decomposable> = arrayOf(
        // P5 P3 C
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.P3, goldenTriangle.points[2]),
        // P5 C P1
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.points[2], goldenTriangle.P1)
    )

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    init {
        //assert_5Triangles_3Gnomons()
    }

}