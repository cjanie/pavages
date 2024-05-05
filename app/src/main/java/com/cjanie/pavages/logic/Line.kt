package com.cjanie.pavages.logic

import com.cjanie.pavages.tools.Trigonometry
import kotlin.math.abs

class Line(val A: Point, val B: Point) {

    // Pythagorean theorem
    // Define square angle AOB
    private val O = Point("O", A.x, B.y)
    private val AO = abs(A.y - O.y)
    private val BO = abs(B.x - O.x)


    fun length(): Double {
        return Trigonometry.hypotenuseLengthFromPythagoreanTheorem(AO, BO)
    }

    fun middle(): Point {
        val midLengthAO = AO / 2.0
        val midLengthBO = BO / 2.0
        return Point(
            x= if (A.x < B.x) A.x + midLengthBO else A.x - midLengthBO,
            y= if (A.y < B.y) A.y + midLengthAO else A.y - midLengthAO
        )
    }

}