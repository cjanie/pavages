package com.cjanie.pavages.logic

import kotlin.math.sqrt

class GoldenTriangle(
    pointOppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(pointOppositeToBase, basePoint1, basePoint2) {

    companion object {
        val GOLDEN_RATIO_PHI = (1.0 + sqrt(5.0)) / 2.0

        fun createGoldenTriangle(circleEnclosureRadius: Double): GoldenTriangle {
            return Pentagon(circleEnclosureRadius).goldenTriangle
        }
    }

init {
    // check that the duplicated side is in the golden ration phi to the base side
    assert((duplicatedSideLength() / baseSideLength()).toFloat() == GOLDEN_RATIO_PHI.toFloat())
}


}