package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class GoldenTriangleDecomposables_Dart_atBottom_sym(override var goldenTriangle: GoldenTriangle): GoldenTriangleDecomposables_Kite_Dart(goldenTriangle), DecomposablesModel {

    override fun kite(): Array<Decomposable> = arrayOf(
        // original symP1 P5, P1
        // sym = P1 symP1, symP5
        GoldenTriangle(goldenTriangle.P1, goldenTriangle.symP1, goldenTriangle.symP5),
        // original symP1 P3 P5
        // sym = P1, symP5, symP3
        GoldenTriangle(goldenTriangle.P1, goldenTriangle.symP5, goldenTriangle.symP3)
    )

    override fun dart(): Array<Decomposable> = arrayOf(
        // original P5 P3 C
        // sym = symP5 B symP3
        GoldenGnomon(goldenTriangle.symP5, goldenTriangle.points[1], goldenTriangle.symP3),
        // original P5 C P1
        // sym = symP5 P1sym B
        GoldenGnomon(goldenTriangle.symP5, goldenTriangle.symP1, goldenTriangle.points[1])
    )

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        return GoldenTriangleDecomposables_Dart_atBottom(goldenTriangle)
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