package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Kite_atBottom_sym(override var goldenTriangle: GoldenTriangle): GoldenTriangleDecomposables_Kite_Dart(goldenTriangle), DecomposablesModel {

    // Original Kite and Dart P3 C P1 symP1
    override fun kite(): Array<Decomposable> = arrayOf(
        // Original C P1 P4
        // sym B P4 P1
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.P4, goldenTriangle.symP1),
        // Original C P4 P3
        // sym = B symP3 P4
        GoldenTriangle(goldenTriangle.points[1], goldenTriangle.symP3, goldenTriangle.P4)
    )

    override fun dart(): Array<Decomposable> = arrayOf(
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

    override fun createModel(goldenTriangle: GoldenTriangle, arrange: Boolean): GoldenTriangleDecomposables_Kite_Dart {
        if(arrange)
            return GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle)
        else return GoldenTriangleDecomposables_Dart_atBottom_sym(goldenTriangle)
    }

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        return GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle)
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }

    override fun decomposables() : Array<Decomposable> = arrayOf(
        *kite(), *dart()
    )



    init {
        //assert_5Triangles_3Gnomons()
    }
}