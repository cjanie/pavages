package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Graph2D {

    val goldenTriangle = GoldenTriangle.create(baseLength = 200.0)

    val decomposeStep1 = goldenTriangle.decomposeStep1

    val decomposeStep2 = goldenTriangle.decomposeStep2

    val decomposeStep2ArrangeAdjacentGoldenTriangles = goldenTriangle.decomposeStep2ArrangeAdjacentGoldenTriangles

    val decomposeStep3 = goldenTriangle.decomposeStep3

    val decomposeStep4 = goldenTriangle.decomposeStep4
}