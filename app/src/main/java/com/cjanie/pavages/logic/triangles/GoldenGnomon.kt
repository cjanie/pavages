package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.NumberConstants
import com.cjanie.pavages.logic.Pentagon
import com.cjanie.pavages.logic.Point

class GoldenGnomon(
    pointOppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(pointOppositeToBase, basePoint1, basePoint2) {

    companion object {
        fun createGoldenGnomons(pentagon: Pentagon): Array<GoldenGnomon> {
            return pentagon.goldenGnomons
        }
    }

    init {
        // check that the duplicated side is in the golden ration 1 / phi to the base side
        assert((duplicatedSideLength() / baseSideLength()).toFloat() == (1 / NumberConstants.GOLDEN_NUMBER_PHI).toFloat())
    }
}