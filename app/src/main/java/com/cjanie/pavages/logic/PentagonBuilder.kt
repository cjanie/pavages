package com.cjanie.pavages.logic

import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry

class PentagonBuilder(private val enclosure: Cercle) {

    // circle enclosure of center O
    private val O = enclosure.center.copy(name = "O")

    // Vertical axis AA'
    private val A = Point("A", O.x, O.y + enclosure.radius)
    private val AA = Point("A'", O.x, O.y - enclosure.radius)

    // K on the vertical axis in the middle between O and A'
    val K = Point("K", O.x, O.y - Line(O, AA).length() / 2.0)

    // B' on the horizontal Axis
    val BB = Point("B'", O.x - enclosure.radius, O.y)

    // U on the vertical axis between O and A, on the circle of centre K and radius KB'
    val U = Point("U", O.x, K.y + Line(K, BB).length())

    // I on the vertical axis, in the middle between O and U
    private val I = Point("I", O.x, O.y + Line(O, U).length() / 2.0)

    // BI as horizontal line. Sym E
    private val B_I = Trigonometry.side2LengthFromPythagoreTheorem(enclosure.radius, Line(O, I).length())
    private val B = Point("B", I.x - B_I, I.y)
    private val E = Symmetry.symmetryByVerticalAxis(I.x, B, "E")

    // P on the vertical axis, in the middle between O and K
    private val P = Point("P", O.x, O.y - Line(O, K).length() / 2)

    // J on the vertical axis, on the circle of center P and radius IP
    private val J = Point("J", O.x, P.y - Line(I, P).length())

    // CJ as horizontal line. Sym D
    private val j_c = Trigonometry.side2LengthFromPythagoreTheorem(enclosure.radius, Line(O, J).length())
    private val C = Point("C", J.x - j_c, J.y)
    private val D = Symmetry.symmetryByVerticalAxis(J.x, C, "D")

    fun build(): Pentagon {
        return Pentagon(arrayOf(A, B, C, D, E))
    }
}