package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry

abstract class GoldenTriangleDecomposables_2Triangles_1Gnomon(goldenTriangle: GoldenTriangle) {

    // iteration 1 returns P1, symP1, P2
    // Golden triangle BCP : base = CP, duplicated sides = BC and BP
    val duplicatedSidesLength = goldenTriangle.points[2].x - goldenTriangle.points[1].x

    // P1 coordinates
    val P1 = Point(
        x = goldenTriangle.points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
        y = goldenTriangle.points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)
    )

    // Sym P1
    val symP1 = Symmetry.symmetryByVerticalAxis(goldenTriangle.points[0].x, P1)

    // triangle symP1, P1, P2: base = P1 P2, duplicated sides = symP1 P1 and sym P1 P2
    val duplicatedSideSymP1_P1Length = P1.x - symP1.x
    // P2 coordinates
    val P2 = Point(
        x = symP1.x
                + Trigonometry.adjacentSideLength(duplicatedSideSymP1_P1Length, 36.0),
        y = symP1.y
                + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
            duplicatedSideSymP1_P1Length, 36.0
        )
    )

    // At first iteration: 3 points P1 P1, symP1, P2 as 3 decomposables

    abstract val goldenTriangles: Array<GoldenTriangle>
    abstract val goldenGnomon: GoldenGnomon

    protected fun assert_2Triangles_1Gnomon() {
        assert(goldenTriangles.size == 2)
        assert(goldenGnomon != null)
    }

}