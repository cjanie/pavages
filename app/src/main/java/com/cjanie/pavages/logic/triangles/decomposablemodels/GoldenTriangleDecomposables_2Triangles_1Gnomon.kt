package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry

abstract class GoldenTriangleDecomposables_2Triangles_1Gnomon : DecomposablesModel {


    // At first iteration: 3 points P1 P1, symP1, P2 as 3 decomposables

    abstract fun goldenTriangles(): Array<GoldenTriangle>
    abstract fun goldenGnomon(): GoldenGnomon
    abstract override fun decomposables(): Array<Decomposable>

    abstract fun arrange(): GoldenTriangleDecomposables_2Triangles_1Gnomon

    protected fun assert_2Triangles_1Gnomon() {
        assert(goldenTriangles().size == 2)
        assert(goldenGnomon() != null)
        assert(decomposables().size == 3)
    }

}