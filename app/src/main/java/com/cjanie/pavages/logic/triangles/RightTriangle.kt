package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Line
import com.cjanie.pavages.logic.Point
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.pow



class RightTriangle(rightAngle: Point, hypothenuse: Line) {

    // Pythagorean Theorem
    init {
        val side1 = Line(rightAngle, hypothenuse.A)
        val side2 = Line(rightAngle, hypothenuse.B)
        assert(
            hypothenuse.length().pow(2) == side1.length().pow(2) + side2.length().pow(2)
        )

        // Angle A
        val adjacent = Line(hypothenuse.A, rightAngle)
        val opposite = Line(hypothenuse.B, rightAngle)

        val cosA = adjacent.length() / hypothenuse.length()
        val sinA = opposite.length() / hypothenuse.length()
        val tangA = opposite.length() / adjacent.length()

        val angleA = acos(cosA)
    }




}