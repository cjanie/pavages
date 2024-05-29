package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_Kite_Dart(open var goldenTriangle: GoldenTriangle): DecomposablesModel {

    companion object {
        fun createModel(
            kiteDartModelType: KyteDartModel,
            goldenTriangle: GoldenTriangle
        ): GoldenTriangleDecomposables_Kite_Dart {
            return when(kiteDartModelType) {
                KyteDartModel.KITE_AT_BOTTOM
                -> GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle)
                KyteDartModel.KITE_AT_BOTTOM_SYM
                -> GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle)
                KyteDartModel.DART_AT_BOTTOM
                -> GoldenTriangleDecomposables_Dart_atBottom(goldenTriangle)
                KyteDartModel.DART_AT_BOTTOM_SYM
                -> GoldenTriangleDecomposables_Dart_atBottom_sym(goldenTriangle)
            }
        }

    }
    abstract fun kite(): Array<Decomposable>
    abstract fun dart(): Array<Decomposable>

    abstract fun createModel(goldenTriangle: GoldenTriangle, arrange: Boolean): GoldenTriangleDecomposables_Kite_Dart

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