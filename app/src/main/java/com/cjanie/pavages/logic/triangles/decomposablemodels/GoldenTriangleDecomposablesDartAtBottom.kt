package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposablesDartAtBottom(goldenTriangle: GoldenTriangle):
GoldenTriangleDecomposables_Kite_Dart(goldenTriangle) {
    override fun kite(): Array<GoldenTriangle> = arrayOf(
        // symP1 P5, P1
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P5, goldenTriangle.P1),
        // symP1 P3 P5
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P3, goldenTriangle.P5)
    )

    override fun dart(): Array<GoldenGnomon> = arrayOf(
        // P5 P3 C
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.P3, goldenTriangle.points[2]),
        // P5 C P1
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.points[2], goldenTriangle.P1)
    )

    override fun topGoldenTriangleDecomposables(): Array<Decomposable> = arrayOf(
        // symP1 P2 P6
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P2, goldenTriangle.P6),
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P1, goldenTriangle.P2),
        // P6 P2 A
        GoldenGnomon(goldenTriangle.P6, goldenTriangle.P2, goldenTriangle.points[0])

    )
    init {
        assert_5Triangles_3Gnomons()
    }

}