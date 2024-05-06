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

    init {

        // check that the duplicated side is in the golden ration phi to the base side
        val decimal = BigDecimal(duplicatedSideLength() / baseSideLength()).setScale(1, RoundingMode.DOWN)
        println("${duplicatedSideLength() / baseSideLength()}!!!!!!!!!!!!!!!!!!")
        val phi = BigDecimal(Number.GOLDEN_NUMBER_PHI).setScale(1, RoundingMode.DOWN)
        println("${Number.GOLDEN_NUMBER_PHI}!!!!!!!!!!!!!!!!!!")
        assert(decimal == phi)//((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
    }

    companion object {
        // Golden ratio: 1:phi:phi
        val duplicatedSidesRatio = Number.GOLDEN_NUMBER_PHI
        val angleAtBaseDegrees = 72.0

        fun create(baseLength: Double): GoldenTriangle {

            // Golden ratio
            val duplicatedSidesLength = duplicatedSidesRatio * baseLength
            // baselength =
            // Get height by trigonometry
            val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength,
                angleAtBaseDegrees
            )
            // Set points coordinates
            // A, opposite to base, on vertical Axis
            val A = Point(x = 0.0, y = height)
            // Base BC on horizontal Axis
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)
        }
    }

    fun decomposeStep1(): DecomposeStep1 {
        return DecomposeStep1()
    }

    inner class DecomposeStep1 {
        // Create golden triangle BCP of base CP
        private val duplicatedSidesLength = points[2].x - points[1].x

        // We have the angle at base in B of 36° and hypotenuse
        // coordinates of P
        private val P = Point(
            x = points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )
        val goldenTriangle = GoldenTriangle(points[1], points[2], P)
        val goldenGnomon = GoldenGnomon(P, points[0], points[1])

    }




}