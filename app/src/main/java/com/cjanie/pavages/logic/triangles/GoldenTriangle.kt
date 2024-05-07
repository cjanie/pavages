package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry
import java.math.BigDecimal
import java.math.RoundingMode

class GoldenTriangle(
    oppositeToBase: Point = Point(x = 0.0, y = height),
    basePoint1: Point = Point( x = -baseRatio / 2.0, y = 0.0),
    basePoint2: Point = Point( x = baseRatio / 2.0, y = 0.0)
): IsoscelesTriangle(oppositeToBase, basePoint1, basePoint2),
    Decomposable {

    val decomposeStep1 by lazy { DecomposeStep1() }
    val decomposeStep2 by lazy { DecomposeStep2() }
    val decomposeStep2ArrangeAdjacentGoldenTriangles by lazy { DecomposeStep2ArrangeAdjacentGoldenTriangle() }
    val decomposeStep3 by lazy { DecomposeStep3() }
    val decomposeStep4 by lazy { DecomposeStep4() }

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
        val baseRatio = 1.0
        val angleAtBaseDegrees = 72.0
        val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
            duplicatedSidesRatio,
            angleAtBaseDegrees)

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

    fun iterate(iteration: Int): Array<Decomposable> {
        if(iteration == 1)
            return arrayOf(decomposeStep2.goldenGnomon, decomposeStep2.goldenTriangles[0], decomposeStep2.goldenTriangles[1])
        // If iteration is 0, return the initial golden triangle
        else return arrayOf(this)
    }
    inner class DecomposeStep1 {
        // Create golden triangle BCP of base CP
        private val duplicatedSidesLength = points[2].x - points[1].x

        // We have the angle at base in B of 36° and hypotenuse
        // coordinates of P
        val P = Point(
            x = points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )
        val goldenTriangle = GoldenTriangle(points[1], points[2], P)
        val goldenGnomon = GoldenGnomon(P, points[0], points[1])
    }

    inner class DecomposeStep2 {
        // 2 golden triangle, 1 golden gnomon
        // 4 arrangements possible
        // Case: the 2 golden triangles are not adjacent

        val P = Symmetry.symmetryByVerticalAxis(0.0, decomposeStep1.P)
        val goldenGnomon = GoldenGnomon(P, points[1], decomposeStep1.P)
        val goldenTriangles = arrayOf(
            decomposeStep1.goldenTriangle,
            GoldenTriangle(points[0], P, decomposeStep1.P)
        )
    }

    inner class DecomposeStep2ArrangeAdjacentGoldenTriangle {
        // triangle A, PP, P
        val duplicatedSidesLength = decomposeStep1.P.x - decomposeStep2.P.x

        val P = Point(
            x = decomposeStep2.goldenTriangles[1].points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = decomposeStep2.goldenTriangles[1].points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )

        val goldenGnomon = GoldenGnomon(P, points[0], decomposeStep2.P)

        val goldenTriangle = Triangle(arrayOf(decomposeStep2.P, decomposeStep1.P, P))

        val goldenTriangles = arrayOf(
            GoldenTriangle(points[2], decomposeStep2.P, points[1]),
            GoldenTriangle(points[2], P, decomposeStep2.P)
        )

    }

    inner class DecomposeStep3 {

        val duplicatedSidesLenght = decomposeStep2ArrangeAdjacentGoldenTriangles.P.x - Symmetry.symmetryByVerticalAxis(0.0, decomposeStep2ArrangeAdjacentGoldenTriangles.P).x
        private val step2Sym = Symmetry.symmetryByVerticalAxis(0.0, decomposeStep2ArrangeAdjacentGoldenTriangles.P)
        val goldenTriangle = GoldenTriangle(points[0], step2Sym, decomposeStep2ArrangeAdjacentGoldenTriangles.P)
        val goldenGnomon = GoldenGnomon(step2Sym, decomposeStep2.P, decomposeStep2ArrangeAdjacentGoldenTriangles.P)

        val baseGoldenTriangle = GoldenTriangle(
            decomposeStep2.P,
            points[1],
            Point(
                x = points[1].x + decomposeStep2ArrangeAdjacentGoldenTriangles.P.x - step2Sym.x,
                y = points[1].y
            )
        )
        val baseGoldeGnomon = GoldenGnomon(
            baseGoldenTriangle.points[2],
            points[2],
            decomposeStep2.P
        )
        val otherGoldenTriangle = GoldenTriangle(
            decomposeStep2.P,
            decomposeStep1.P,
            decomposeStep2ArrangeAdjacentGoldenTriangles.P
        )
        val otherGoldenGnomon = GoldenGnomon(
            decomposeStep1.P,
            decomposeStep2.P,
            points[2]
        )

        // Kite and Dart from losange
        val P = Point(
            x = 0.0,
            y = otherGoldenTriangle.points[2].y - otherGoldenTriangle.points[1].y)
        val goldenGnomon1 = GoldenGnomon(
            P,
            otherGoldenTriangle.points[1],
            otherGoldenTriangle.points[0]
        )

        val goldenGnomon2 = GoldenGnomon(
            P,
            baseGoldenTriangle.points[0],
            baseGoldenTriangle.points[2]
        )

        val goldenTriangle1 = GoldenTriangle(points[2], goldenGnomon1.points[1], goldenGnomon1.points[0])
        val goldenTriangle2 = GoldenTriangle(points[2], goldenGnomon2.points[0], goldenGnomon2.points[2])


    }

    inner class DecomposeStep4 {
        // Kite and Dart from losange
        val P = Point(
            x = 0.0,
            y = decomposeStep3.otherGoldenTriangle.points[2].y - decomposeStep3.otherGoldenTriangle.points[1].y)
        val goldenGnomon = GoldenGnomon(
            P,
            decomposeStep3.otherGoldenTriangle.points[1],
            decomposeStep3.otherGoldenTriangle.points[0]
        )

        val goldenGnomon2 = GoldenGnomon(
            P,
            decomposeStep3.baseGoldenTriangle.points[0],
            decomposeStep3.baseGoldenTriangle.points[2]
        )

    }

}