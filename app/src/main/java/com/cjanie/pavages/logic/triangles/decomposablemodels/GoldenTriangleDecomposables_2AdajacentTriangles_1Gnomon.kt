package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2AdajacentTriangles_1Gnomon(val goldenTriangle: GoldenTriangle, val pointsToDecompose: Array<Point>) {


    val goldenTriangles = arrayOf(
        // C symP1 B: base = symP1 B
        GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[0], goldenTriangle.points[1]),
        // C P2 symP1: base = P2 symP1
        GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[1], pointsToDecompose[0])
    )

    val goldenGnomon = GoldenGnomon(pointsToDecompose[1], goldenTriangle.points[0], pointsToDecompose[0])


}