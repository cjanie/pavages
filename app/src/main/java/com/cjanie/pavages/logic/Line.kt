package com.cjanie.pavages.logic

import com.cjanie.pavages.tools.TrigonometryTools
import kotlin.math.abs

class Line(private val A: Point, private val B: Point) {

    fun length(): Double {
            // Pythagorean theorem
            val O = Point("O", A.x, B.y)
            val AO = abs(A.y - O.y)
            val BO = abs(B.x - O.x)
            return TrigonometryTools.hypotenuseLengthFromPythagoreanTheorem(AO, BO)
    }
}