package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Dart_atBottom_sym(val goldenTriangle: GoldenTriangle): DecomposablesModel {

    fun kite(): Array<GoldenTriangle> = arrayOf(
        // original symP1 P5, P1
        // sym = P1 symP1, symP5
        GoldenTriangle(goldenTriangle.P1, goldenTriangle.symP1, goldenTriangle.symP5),
        // original symP1 P3 P5
        // sym = P1, symP5, symP3
        GoldenTriangle(goldenTriangle.P1, goldenTriangle.symP5, goldenTriangle.symP3)
    )

    fun dart(): Array<GoldenGnomon> = arrayOf(
        // original P5 P3 C
        // sym = symP5 B symP3
        GoldenGnomon(goldenTriangle.symP5, goldenTriangle.points[1], goldenTriangle.symP3),
        // original P5 C P1
        // sym = symP5 P1sym B
        GoldenGnomon(goldenTriangle.symP5, goldenTriangle.symP1, goldenTriangle.points[1])
    )

    fun topGoldenTriangleDecomposables(): Array<Decomposable> {
        val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1)
        val decomposeTop = GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym(bigGoldenTriangleTop)
        return arrayOf(*decomposeTop.goldenTriangles, decomposeTop.goldenGnomon)
    }
    private val bottomGoldenTriangle =  GoldenTriangle(
        // Original symP1 B P3
        // sym = P1 symP3 C
        goldenTriangle.P1,
        goldenTriangle.symP3,
        goldenTriangle.points[2]
    )

    override val decomposables : Array<Decomposable> = arrayOf(
        *kite(), *dart(), *topGoldenTriangleDecomposables(), bottomGoldenTriangle
    )

    init {
        //assert_5Triangles_3Gnomons()
    }
}