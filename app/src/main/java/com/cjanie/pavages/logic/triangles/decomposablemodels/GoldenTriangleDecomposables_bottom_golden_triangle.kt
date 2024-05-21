package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry

class GoldenTriangleDecomposables_bottom_golden_triangle(var goldenTriangle: GoldenTriangle): DecomposablesModel {

    override fun goldenTriangle(): GoldenTriangle {
        TODO("Not yet implemented")
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        TODO("Not yet implemented")
    }

    override fun decomposables(): Array<Decomposable> {
      return arrayOf(GoldenTriangle(goldenTriangle.symP1, goldenTriangle.points[1], goldenTriangle.P3))

    }


}