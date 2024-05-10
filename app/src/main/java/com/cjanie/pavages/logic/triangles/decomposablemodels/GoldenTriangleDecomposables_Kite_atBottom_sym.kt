package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry

class GoldenTriangleDecomposables_Kite_atBottom_sym(val goldenTriangle: GoldenTriangle) {

    // Original Kite and Dart P3 C P1 symP1
     fun kite(): Array<GoldenTriangle> = arrayOf(
        // Original C P1 P4
        // sym B P4 P1
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.P4, goldenTriangle.symP1),
        // Original C P4 P3
        // sym = B symP3 P4
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.symP3, goldenTriangle.P4)
    )

    fun dart() = arrayOf(
        // Original P4 P1 symP1
        // sym = P4 symP1 P1
        GoldenGnomon(
            goldenTriangle.P4,
            goldenTriangle.symP1, // original P1,
            goldenTriangle.P1 // original symP1
        ),
        GoldenGnomon(
            // Original P4 symP1 P3
            // sym P4 symP3 P1
            goldenTriangle.P4, // P4,
            goldenTriangle.symP3, // symP1,
            goldenTriangle.P1 // P3
        )
    )

     fun topGoldenTriangleDecomposables(): Array<Decomposable> {
        val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1)
        val decomposeTop = GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(bigGoldenTriangleTop)
        return arrayOf(*decomposeTop.goldenTriangles, decomposeTop.goldenGnomon)

    }

    // Original symP1 B P3
    // sym = P1 symP3 C
    private val bottomGoldenTriangle =  GoldenTriangle(
        goldenTriangle.P1,
        goldenTriangle.symP3,
        goldenTriangle.points[2]
    )

    val decomposables : Array<Decomposable> = arrayOf(
        *kite(), *dart(), *topGoldenTriangleDecomposables(), bottomGoldenTriangle
    )



    init {
        //assert_5Triangles_3Gnomons()
    }
}