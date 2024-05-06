package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Graph2D {

    val goldenTriangle = GoldenTriangle.create(baseLength = 200.0)

    private val decomposeStep1 = goldenTriangle.decomposeStep1()
    val decomposeGoldenTriangle = decomposeStep1.goldenTriangle
    val decomposeGoldenGnomon = decomposeStep1.goldenGnomon



}