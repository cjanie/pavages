package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_Kite_Dart(open var goldenTriangle: GoldenTriangle): DecomposablesModel {

    abstract fun kite(): Array<GoldenTriangle>
    abstract fun dart(): Array<GoldenGnomon>

    override fun decomposables(): Array<Decomposable> = arrayOf(
    *kite(), *dart()
    )

    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
    }



    protected fun assert_5Triangles_3Gnomons() {

    }
}