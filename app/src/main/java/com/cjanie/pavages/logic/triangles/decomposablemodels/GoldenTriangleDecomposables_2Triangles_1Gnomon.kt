package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry

abstract class GoldenTriangleDecomposables_2Triangles_1Gnomon(goldenTriangle: GoldenTriangle) {


    // At first iteration: 3 points P1 P1, symP1, P2 as 3 decomposables

    abstract val goldenTriangles: Array<GoldenTriangle>
    abstract val goldenGnomon: GoldenGnomon
    abstract val decomposables: Array<Decomposable>

    protected fun assert_2Triangles_1Gnomon() {
        assert(goldenTriangles.size == 2)
        assert(goldenGnomon != null)
        assert(decomposables.size == 3)
    }

}