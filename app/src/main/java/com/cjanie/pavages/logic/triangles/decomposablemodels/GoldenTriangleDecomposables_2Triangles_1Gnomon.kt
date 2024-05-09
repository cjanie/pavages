package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle

abstract class GoldenTriangleDecomposables_2Triangles_1Gnomon {

    abstract val goldenTriangles: Array<GoldenTriangle>
    abstract val goldenGnomon: GoldenGnomon

    protected fun assert_2Triangles_1Gnomon() {
        assert(goldenTriangles.size == 2)
        assert(goldenGnomon != null)
    }

}