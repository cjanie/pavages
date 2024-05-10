package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym(val goldenTriangle: GoldenTriangle): GoldenTriangleDecomposables_2Triangles_1Gnomon(goldenTriangle) {
    // Original One golden triangle BCP1 of base CP1 + One Big Gnomon P1 A B of base AB
    // sym = C symP B
    private val goldenTriangleBCP1 = GoldenTriangle(goldenTriangle.points[2], goldenTriangle.symP1, goldenTriangle.points[1])
    // Big golden gnomonP1AB to decompose:
    // One Gnomon can be decomposed into One golden triangle and one golden gnomon
    private val bigGoldenGnomonDecomposables1Triangle1Gnomon = GoldenGnomonDecomposables_1Triangle_1Gnomon (
        // Original Triangle A symP1 P1 of base symP1 P1
        // sym : same
        GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1),
        // + 1 Gnomon symP1 B P1 of base B P1
        // sym = P1 symP1 C
        GoldenGnomon(goldenTriangle.P1, goldenTriangle.symP1, goldenTriangle.points[2])
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

    override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, goldenGnomon)

    init {
        assert_2Triangles_1Gnomon()
    }

}