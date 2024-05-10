package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Dart_atBottom(val goldenTriangle: GoldenTriangle) {
    fun kite(): Array<GoldenTriangle> = arrayOf(
        // symP1 P5, P1
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P5, goldenTriangle.P1),
        // symP1 P3 P5
        GoldenTriangle(goldenTriangle.symP1, goldenTriangle.P3, goldenTriangle.P5)
    )

    fun dart(): Array<GoldenGnomon> = arrayOf(
        // P5 P3 C
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.P3, goldenTriangle.points[2]),
        // P5 C P1
        GoldenGnomon(goldenTriangle.P5, goldenTriangle.points[2], goldenTriangle.P1)
    )

    fun topGoldenTriangleDecomposables(): Array<Decomposable> {
        val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1)
        val decomposeTop = GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(bigGoldenTriangleTop)
        return arrayOf(*decomposeTop.goldenTriangles, decomposeTop.goldenGnomon)
    }
    private val bottomGoldenTriangle =  GoldenTriangle(
        goldenTriangle.symP1, //symP1,
        goldenTriangle.points[1], // B
        goldenTriangle.P3 //P3
    )

    val decomposables : Array<Decomposable> = arrayOf(
        *kite(), *dart(), *topGoldenTriangleDecomposables(), bottomGoldenTriangle
    )

    init {
        //assert_5Triangles_3Gnomons()
    }

}