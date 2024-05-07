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

    val decompose1NonAdjacentGoldenTrianglesArrangement by lazy { Decompose1NonAdjacentGoldenTrianglesArrangement() }
    val decomposeStep1AdjacentGoldenTrianglesArrangement by lazy { Decompose1AdjacentGoldenTrianglesArrangement() }
    //val decomposeStep2 by lazy { Decompose2Arrange()} //DecomposeStep2() }
    val decomposeStep2 by lazy { DecomposeStep2() }
    //val decompose2Arrange by lazy { Decompose2Arrange() }


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

    fun iterate(iteration: Int, arrange: Boolean = false): Array<Decomposable> {
        if(iteration == 1) {
            if(arrange) {
                // Case adjacent golden triangle arrangement
                return decomposeStep1AdjacentGoldenTrianglesArrangement.decomposables
            }
            // Case non adjacent golden triangle arrangement
            return decompose1NonAdjacentGoldenTrianglesArrangement.decomposables
        } else if (iteration == 2) {
            return decomposeStep2.decomposables
        }

        // If iteration is 0, return the initial golden triangle
        else return arrayOf(this)
    }
    inner class DecomposeInit {
        // Create golden triangle BCP of base CP
        private val duplicatedSidesLength = points[2].x - points[1].x

        // We have the angle at base in B of 36° and hypotenuse
        // coordinates of P by trigonometry
        // We have the angle at base in B of 36° and hypotenuse
        val P = Point(
            x = points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )
        val goldenTriangle = GoldenTriangle(points[1], points[2], P)
        val goldenGnomon = GoldenGnomon(P, points[0], points[1])
    }

    abstract inner class Decompose {
        abstract val goldenTriangles: Array<GoldenTriangle>
        abstract val goldenGnomons: Array<GoldenGnomon>
        abstract val decomposables: Array<Decomposable>

        // Golden triangle BCP : base = CP, duplicated sides = BC and BP
        private val duplicatedSidesLength = points[2].x - points[1].x

        // P1 coordinates
        val P1 = Point(
            x = points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)
        )

        // Sym P1
        val symP1 = Symmetry.symmetryByVerticalAxis(points[0].x, P1)

        // triangle symP1, P1, P2: base = P1 P2, duplicated sides = symP1 P1 and sym P1 P2
        val duplicatedSideSymP1_P1Length = P1.x - symP1.x

        // P2 coordinates
        val P2 = Point(
            x = symP1.x
                    + Trigonometry.adjacentSideLength(duplicatedSideSymP1_P1Length, 36.0),
            y = symP1.y
                    + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
                duplicatedSideSymP1_P1Length, 36.0
            )
        )

        val symP2 = Symmetry.symmetryByVerticalAxis(0.0, P2)

    }

    abstract inner class Decompose1: Decompose() {
        // total : 3 = 2 golden triangle + 1 golden gnomon
        protected fun assertion() {
            assert(goldenTriangles.size == 2)
            assert(goldenGnomons.size == 1)
            assert(decomposables.size == 3)
        }

    }

    inner class Decompose1NonAdjacentGoldenTrianglesArrangement: Decompose1() {
        // Total 2 goldenTriangles
        override val goldenTriangles = arrayOf(
            // BCP of base CP
            GoldenTriangle(points[1], points[2], P1),
            // Triangle A symP1 P1 of base symP1 P1
            GoldenTriangle(points[0], symP1, P1)
        )
        // + 1 Gnomon symP1 B P1 of base B P1
        override val goldenGnomons = arrayOf(GoldenGnomon(symP1, points[1], P1))

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    inner class Decompose1AdjacentGoldenTrianglesArrangement: Decompose1() {

        override val goldenTriangles = arrayOf(
            // C symP1 B: base = symP1 B
            GoldenTriangle(points[2], symP1, points[1]),
            // C P2 symP1: base = P2 symP1
            GoldenTriangle(points[2], P2, symP1)
        )

        override val goldenGnomons = arrayOf(GoldenGnomon(P2, points[0], symP1))

        override val decomposables: Array<Decomposable>
            get() = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    open inner class DecomposeStep2: Decompose() {

        val P3 = Point(
            x = points[1].x + P2.x - symP2.x,
            y = points[1].y
        )

        // Kite and Dart from losange P3 C P1 symP1
        val P4 = Point(
            x = 0.0,
            y = P2.y - P1.y)

        // P5 Sym of P2 on horizontal axis P1 symP1
        // val P5 = Symmetry.symmetryByHorizontalAxis(P1.y, P2)
        val P5 = Point(
            x = P2.x,
            y = P1.y - (P2.y - P1.y)
        )
        override val goldenTriangles = arrayOf(
            // Top
            GoldenTriangle(points[0], symP2, P2),
            // base
            GoldenTriangle(
                symP1,
                points[1],
                P3
            ),
            // under top
            GoldenTriangle(
                symP1,
                P1,
                P2
            ),
            // Kite
            GoldenTriangle(points[2], P1, P4),
            GoldenTriangle(points[2], P4, P3)
            )


        override val goldenGnomons = arrayOf(
            GoldenGnomon(symP2, symP1, P2),
            // Dart
            GoldenGnomon(
                P4,
                P1,
                symP1
            ),
            GoldenGnomon(
                P4,
                symP1,
                P3
            )
        )

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)
/*
        init {
            assert(goldenTriangles.size == 5)
            assert(goldenGnomons.size == 3)
            assert(decomposables.size == 8)
        }

 */
        val kite = arrayOf(
                GoldenTriangle(symP1, P5, P1),
                GoldenTriangle(symP1, P3, P5)
        )

        val dart = arrayOf(
            GoldenGnomon(P5, P3, points[2]),
            GoldenGnomon(P5, points[2], P1)
        )

    }
/*
    inner class Decompose2Arrange: DecomposeStep2() {

        override val goldenTriangles = arrayOf(
                GoldenTriangle(symP1, P5, P1),
                GoldenTriangle(symP1, P3, P5)
            )
        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles)

    }

 */

    }
/*

    inner class DecomposeStep4 {
        // Kite and Dart from losange
        val P = Point(
            x = 0.0,
            y = decomposeStep2.otherGoldenTriangle.points[2].y - decomposeStep2.otherGoldenTriangle.points[1].y)
        val goldenGnomon = GoldenGnomon(
            P,
            decomposeStep2.otherGoldenTriangle.points[1],
            decomposeStep2.otherGoldenTriangle.points[0]
        )

        val goldenGnomon2 = GoldenGnomon(
            P,
            decomposeStep2.baseGoldenTriangle.points[0],
            decomposeStep2.baseGoldenTriangle.points[2]
        )

    }


}
*/
