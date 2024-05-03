package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.NumberConstants
import com.cjanie.pavages.logic.Pentagon
import com.cjanie.pavages.logic.Point
import kotlin.math.sqrt

class GoldenTriangle(
    pointOppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(pointOppositeToBase, basePoint1, basePoint2) {

    companion object {
        fun createGoldenTriangle(circleEnclosureRadius: Double): GoldenTriangle {
            return Pentagon(circleEnclosureRadius).goldenTriangle
        }
    }

init {
    // check that the duplicated side is in the golden ration phi to the base side
    assert((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
}


}