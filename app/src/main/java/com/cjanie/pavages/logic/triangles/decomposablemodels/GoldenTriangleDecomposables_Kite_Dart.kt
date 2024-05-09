package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_Kite_Dart(val goldenTriangle: GoldenTriangle) {

    abstract val kite: Array<GoldenTriangle>
    abstract val dart: Array<GoldenGnomon>
    abstract val pyramidion : GoldenTriangle
    abstract val bottomGoldenTriangle : GoldenTriangle
}