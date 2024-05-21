package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_Kite_Dart(open var goldenTriangle: GoldenTriangle): DecomposablesModel {

    abstract fun kite(): Array<GoldenTriangle>
    abstract fun dart(): Array<GoldenGnomon>
    // Big Golden Triangle A symP1 P1 over kite/dart losange
    protected abstract fun topGoldenTriangleDecomposables() : Array<Decomposable>
    protected abstract fun bottomGoldenTriangle(): GoldenTriangle

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