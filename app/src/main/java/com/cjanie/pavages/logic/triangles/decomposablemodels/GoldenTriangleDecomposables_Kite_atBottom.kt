package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Kite_atBottom(val goldenTriangle: GoldenTriangle) {

    // Kite and Dart P3 C P1 symP1
    fun kite(): Array<GoldenTriangle> = arrayOf(
        //C P1 P4
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P1, goldenTriangle.P4),
        // C P4 P3
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P4, goldenTriangle.P3)
    )

    fun dart() = arrayOf(
        GoldenGnomon(
            goldenTriangle.P4, // P4,
            goldenTriangle.P1, // P1,
            goldenTriangle.symP1 // symP1
        ),
        GoldenGnomon(
            goldenTriangle.P4, // P4,
            goldenTriangle.symP1, // symP1,
            goldenTriangle.P3 // P3
        )
    )

     fun topGoldenTriangleDecomposables(): Array<Decomposable> {
        val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1)
        val decomposeTop = GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(bigGoldenTriangleTop)
        return arrayOf(*decomposeTop.goldenTriangles, decomposeTop.goldenGnomon)

    }

    // Original symP1 B P3

    private val bottomGoldenTriangle =  GoldenTriangle(
        goldenTriangle.symP1,
        goldenTriangle.points[1],
        goldenTriangle.P3
    )

    val decomposables : Array<Decomposable> = arrayOf(
        *kite(), *dart(), *topGoldenTriangleDecomposables(), bottomGoldenTriangle
    )



    init {
        //assert_5Triangles_3Gnomons()
    }
}