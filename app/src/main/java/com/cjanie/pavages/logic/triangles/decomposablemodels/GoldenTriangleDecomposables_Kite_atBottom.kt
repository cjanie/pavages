package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle: GoldenTriangle, pointsToDecompose: Array<Point>) {

    // Kite and Dart P3 C P1 symP1
    val kite: Array<GoldenTriangle> = arrayOf(
        //C P1 P4
        GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[0], pointsToDecompose[5]),
        // C P4 P3
        GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[5], pointsToDecompose[4])
    )

    val dart = arrayOf(
        GoldenGnomon(
            pointsToDecompose[5], // P4,
            pointsToDecompose[0], // P1,
            pointsToDecompose[1] // symP1
        ),
        GoldenGnomon(
            pointsToDecompose[5], // P4,
            pointsToDecompose[1], // symP1,
            pointsToDecompose[4] // P3
        )
    )

    val goldenTriangleBottom = GoldenTriangle(
        pointsToDecompose[1], //symP1,
        goldenTriangle.points[1], // B
        pointsToDecompose[4] //P3
    )

    // Big Golden Triangle A symP1 P1 over kite/dart losange
    val bigGoldenTriangleTop = GoldenTriangle(goldenTriangle.points[0], pointsToDecompose[1], pointsToDecompose[0])

}