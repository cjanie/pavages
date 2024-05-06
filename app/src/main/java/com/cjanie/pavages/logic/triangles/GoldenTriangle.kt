package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Trigonometry
import java.math.BigDecimal
import java.math.RoundingMode

class GoldenTriangle(
    oppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(oppositeToBase, basePoint1, basePoint2) {

    companion object {
        // Golden ratio: 1:phi:phi
        val duplicatedSidesRatio = Number.GOLDEN_NUMBER_PHI
        val baseRatio = 1.0
        val angleAtBaseDegrees = 72.0

        fun create(baseLength: Double): GoldenTriangle {

            // Golden ratio
            val hypothenuseLength = GoldenTriangle.duplicatedSidesRatio * baseLength
            val baseLength = GoldenTriangle.baseRatio * baseLength
            // Get height by trigonometry
            val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(hypothenuseLength,
                angleAtBaseDegrees
            )
            // Set points coordinates
            val A = Point(x = 0.0, y = height)
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)

        }

        fun duplicatedSidesLength(baseLength: Double): Double {
            // Golden ratio: 1:phi:phi
            return baseLength * duplicatedSidesRatio
        }
    }

    fun decompose(): GoldenTriangle {
        return this
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