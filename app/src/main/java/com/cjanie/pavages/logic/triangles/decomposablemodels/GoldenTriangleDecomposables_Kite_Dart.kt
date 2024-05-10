package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_Kite_Dart(goldenTriangle: GoldenTriangle) {

    protected abstract fun kite(): Array<GoldenTriangle>
    protected abstract fun dart(): Array<GoldenGnomon>
    // Big Golden Triangle A symP1 P1 over kite/dart losange
    protected abstract fun topGoldenTriangleDecomposables() : Array<Decomposable>
    private val bottomGoldenTriangle =  GoldenTriangle(
        goldenTriangle.symP1, //symP1,
        goldenTriangle.points[1], // B
        goldenTriangle.P3 //P3
    )

    val decomposables : Array<Decomposable> = arrayOf(
        *kite(), *dart(), *topGoldenTriangleDecomposables(), bottomGoldenTriangle
    )


    protected fun assert_5Triangles_3Gnomons() {
        assert(decomposables.filter { it is GoldenTriangle }.size == 5)
        assert(decomposables.filter { it is GoldenGnomon }.size == 3)
    }
}