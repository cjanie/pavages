package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Trigonometry

class GoldenTriangleFactory {

    companion object {

        private val angleAtBaseDegrees = 72.0

        fun create(scale: Double): GoldenTriangle {

            // Golden ratio
            val hypothenuse = Number.GOLDEN_NUMBER_PHI * scale
            val baseLength = 1.0 * scale
            // Get height by trigonometry
            val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(hypothenuse, angleAtBaseDegrees)
            // Set points coordinates
            val A = Point(x = 0.0, y = height)
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)

        }
    }
}