package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

data class GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
    var goldenTriangle: GoldenTriangle
) : GoldenTriangleDecomposables_2Triangles_1Gnomon(), DecomposablesModel {

    // One golden triangle BCP1 of base CP1 + One Big Gnomon P1 A B of base AB
    private fun goldenTriangleBCP1() = GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], goldenTriangle.P1)
    // Big golden gnomonP1AB to decompose:
    // One Gnomon can be decomposed into One golden triangle and one golden gnomon
    private fun bigGoldenGnomonDecomposables1Triangle1Gnomon() = GoldenGnomonDecomposables_1Triangle_1Gnomon (
        // Triangle A symP1 P1 of base symP1 P1
        GoldenTriangle(goldenTriangle.points[0], goldenTriangle.symP1, goldenTriangle.P1),
        // + 1 Gnomon symP1 B P1 of base B P1
        GoldenGnomon(goldenTriangle.symP1, goldenTriangle.points[1], goldenTriangle.P1)
    )

    // Total 2 goldenTriangles
    override fun goldenTriangles() = arrayOf(
        // BCP1 of base CP1
        goldenTriangleBCP1(),
        // GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], pointsToDecompose[0]),
        // Triangle A symP1 P1 of base symP1 P1
        bigGoldenGnomonDecomposables1Triangle1Gnomon().goldenTriangle
    )

    // + 1 Gnomon symP1 B P1 of base B P1
    override fun goldenGnomon() = bigGoldenGnomonDecomposables1Triangle1Gnomon().goldenGnomon
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables(): Array<Decomposable> = arrayOf(*goldenTriangles(), goldenGnomon())
    override fun arrange(): GoldenTriangleDecomposables_2Triangles_1Gnomon {
        return GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(goldenTriangle)
    }

    fun sym(): GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym {
        return GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym(goldenTriangle)
    }
    init {
        //assert_2Triangles_1Gnomon()
    }
}
