package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Kite_atBottom(override var goldenTriangle: GoldenTriangle):
    GoldenTriangleDecomposables_Kite_Dart(goldenTriangle) {

    // Kite and Dart P3 C P1 symP1
    override fun kite(): Array<Decomposable> = arrayOf(
        //C P1 P4
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P1, goldenTriangle.P4),
        // C P4 P3
        GoldenTriangle(goldenTriangle.points[2], goldenTriangle.P4, goldenTriangle.P3)
    )

    override fun dart(): Array<Decomposable> = arrayOf(
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

    override fun createModel(goldenTriangle: GoldenTriangle, arrange: Boolean): GoldenTriangleDecomposables_Kite_Dart {
        if(arrange)
            return GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle)
        else
            return GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle)
    }

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        return GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle)
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    init {
        assert_5Triangles_3Gnomons()
    }
}