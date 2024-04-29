package com.cjanie.pavages.logic

import androidx.compose.ui.geometry.Offset
import com.cjanie.pavages.Point
import com.cjanie.pavages.tools.SymmetryTools
import com.cjanie.pavages.tools.TrigonometryTools
import kotlin.math.pow
import kotlin.math.sqrt

class Pentagon(private val circleRadius: Double) { // A pentagon is enclose in a circle

    companion object {
        // Center
        private val O = Point("O", 0.0, 0.0)
    }

    // Point A at the top of the circle
    private val A = Point("A", O.x, O.y - circleRadius)

    // Point k on vertical axis in the middle of the radius to the bottom
    private val o_k = circleRadius / 2.0
    private val k = Point("K", O.x, O.y + o_k)

    // Point U on vertical axis to the top, on the circle of centre k and radius k_bb
    private val k_bb = TrigonometryTools.hypotenuseLengthFromPythagoreTheorem(
        circleRadius,
        o_k
    )
    private val u = Point("U", O.x, k.y - k_bb)

    // Point I in the middle between O and U
    private val o_u = O.y - u.y
    private val o_i = o_u / 2.0
    private val b_i = TrigonometryTools.side2LengthFromPythagoreTheorem(circleRadius, o_i)

    private val i = Point("I", O.x, O.y - o_i)

    private val B = Point("B", i.x - b_i, i.y)
    private val E = SymmetryTools.symmetryByVerticalAxis(i.x, B, "E")

    private val o_p = o_k / 2F
    private val p = Point ("P", O.x, O.y + o_p)
    private val i_p = p.y - i.y
    private val j = Point("J", O.x, p.y + i_p)

    private val o_j = j.y - O.y
    private val j_c = TrigonometryTools.side2LengthFromPythagoreTheorem(circleRadius, o_j)
    private val C = Point("C", j.x - j_c, j.y)
    private val D = SymmetryTools.symmetryByVerticalAxis(j.x, C, "D")

    val pentagonPoints = arrayOf(A, B, C, D, E)
    val triangle = arrayOf(pentagonPoints[0], pentagonPoints[2], pentagonPoints[3])

    fun circleEnclosure(): CircleEnclosure {
        return CircleEnclosure()
    }

    fun verticalAxis(): VerticalDiameter {
        return VerticalDiameter()
    }

    fun horizontalRadius(): HorizontalRadius {
        return HorizontalRadius()
    }

    fun intermediateCircle(): IntermediaryCircle {
        return IntermediaryCircle()
    }

    fun pentagonHorizontalDiagonal(): PentagonHorizontalDiagonal {
        return PentagonHorizontalDiagonal()
    }

    fun innerCircle(): InnerCircle {
        return InnerCircle()
    }

    fun pentagonBottomSide(): PentagonBottomSide {
        return PentagonBottomSide()
    }

    inner class CircleEnclosure {
        val center = O
        val radius = circleRadius
    }

    inner class VerticalDiameter {
        val top = A
        val bottom = SymmetryTools.symmetryByHorizontalAxis(O.y, A)
    }

    inner class HorizontalRadius {
        val center = O
        // Point bb at the left of the circle, on the horizontal axis
        val left = Point("B'", O.x - circleRadius, O.y)
    }

    inner class IntermediaryCircle {
        val center = k
        val radius = k_bb
        val top = u
    }

    inner class PentagonHorizontalDiagonal {
        val center = i
        val left = B
        val right = E
    }

    inner class InnerCircle {
        val center = p
        val radius = i_p
        val bottom = j
    }

    inner class PentagonBottomSide {
        val left = C
        val right = D
    }
}