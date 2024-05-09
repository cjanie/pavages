package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle: GoldenTriangle) {

    // Kite and Dart P3 C P1 symP1
    val kite: Array<GoldenTriangle> = arrayOf(
        //C P1 P4
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P1, goldenTriangle.P4),
        // C P4 P3
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P4, goldenTriangle.P3)
    )

    val dart = arrayOf(
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

    val goldenTriangleBottom = GoldenTriangle(
        goldenTriangle.symP1, //symP1,
        goldenTriangle.points[1], // B
        goldenTriangle.P3 //P3
    )

    // Big Golden Triangle A symP1 P1 over kite/dart losange
    val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1)

}