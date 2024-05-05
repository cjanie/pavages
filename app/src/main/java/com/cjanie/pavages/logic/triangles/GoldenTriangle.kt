package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Line
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Pentagon
import com.cjanie.pavages.logic.Point
import java.math.BigDecimal
import java.math.RoundingMode

class GoldenTriangle(
    oppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(oppositeToBase, basePoint1, basePoint2) {

    companion object {
        fun create(pentagon: Pentagon): GoldenTriangle {
            return pentagon.goldenTriangle
        }

        fun duplicatedSidesLength(baseLength: Double): Double {
            // Golden ratio: 1:phi:phi
            return baseLength * Number.GOLDEN_NUMBER_PHI
        }

        fun createPointOppositeToBase(basePoint1: Point, basePoint2: Point) {
            val base = Line(basePoint1, basePoint2)
            val baseMiddlePoint = base.middle()
        }
    }

init {

    // check that the duplicated side is in the golden ration phi to the base side
    val decimal = BigDecimal(duplicatedSideLength() / baseSideLength()).setScale(1, RoundingMode.DOWN)
    println("${duplicatedSideLength() / baseSideLength()}!!!!!!!!!!!!!!!!!!")
    val phi = BigDecimal(Number.GOLDEN_NUMBER_PHI).setScale(1, RoundingMode.DOWN)
    println("${Number.GOLDEN_NUMBER_PHI}!!!!!!!!!!!!!!!!!!")
    assert(decimal == phi)//((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
}


}