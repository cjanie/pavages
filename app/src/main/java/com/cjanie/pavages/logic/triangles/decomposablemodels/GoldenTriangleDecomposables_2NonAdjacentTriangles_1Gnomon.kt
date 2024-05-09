package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
    val goldenTriangle: GoldenTriangle, val pointsToDecompose: Array<Point>
) : GoldenTriangleDecomposables_2Triangles_1Gnomon() {
    // One golden triangle BCP1 of base CP1 + One Big Gnomon P1 A B of base AB
    private val goldenTriangleBCP1 = GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], pointsToDecompose[0])
    // Big golden gnomonP1AB to decompose:
    // One Gnomon can be decomposed into One golden triangle and one golden gnomon
    private val bigGoldenGnomonDecomposables1Triangle1Gnomon = GoldenGnomonDecomposables_1Triangle_1Gnomon (
        // Triangle A symP1 P1 of base symP1 P1
        GoldenTriangle(goldenTriangle.points[0], pointsToDecompose[1], pointsToDecompose[0]),
        // + 1 Gnomon symP1 B P1 of base B P1
        GoldenGnomon(pointsToDecompose[1], goldenTriangle.points[1], pointsToDecompose[0])
    )

    // Total 2 goldenTriangles
    override val goldenTriangles = arrayOf(
        // BCP1 of base CP1
        goldenTriangleBCP1,
        // GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], pointsToDecompose[0]),
        // Triangle A symP1 P1 of base symP1 P1
        bigGoldenGnomonDecomposables1Triangle1Gnomon.goldenTriangle
    )

    // + 1 Gnomon symP1 B P1 of base B P1
    override val goldenGnomon = bigGoldenGnomonDecomposables1Triangle1Gnomon.goldenGnomon
    init {
        assert_2Triangles_1Gnomon()
    }
}
