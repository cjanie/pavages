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

    val decomposeInit by lazy { DecomposeInit() }
    val decompose1NonAdjacentGoldenTrianglesArrangement by lazy { Decompose1NonAdjacentGoldenTrianglesArrangement() }
    val decomposeStep1AdjacentGoldenTrianglesArrangement by lazy { Decompose1AdjacentGoldenTrianglesArrangement() }
    val decomposeStep2 by lazy { DecomposeStep2() }


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
        // coordinates of P
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

    }

    abstract inner class Decompose1: Decompose() {

        protected fun assertion() {
            assert(goldenTriangles.size == 2)
            assert(goldenGnomons.size == 1)
            assert(decomposables.size == 3)
        }

    }

    inner class Decompose1NonAdjacentGoldenTrianglesArrangement: Decompose1() {
        // 2 golden triangle, 1 golden gnomon


        // Create the golden triangle1 BCP of base CP
        // Define coordinates of P by trigonometry
        // We have the angle at base in B of 36° and hypotenuse

        private val duplicatedSidesLength = points[2].x - points[1].x

        // P coordinates
        val P = Point(
            x = points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )
        private val goldenTriangle1 = GoldenTriangle(points[1], points[2], P)

        // Second Triangle A, base symP P
        val symP = Symmetry.symmetryByVerticalAxis(0.0, P)
        private val goldenTriangle2 = GoldenTriangle(points[0], symP, P)

        // Total 2 goldenTriangles
        override val goldenTriangles = arrayOf(
            goldenTriangle1,
            goldenTriangle2
        )
        // + 1 Gnomon
        override val goldenGnomons = arrayOf(GoldenGnomon(symP, points[1], P))

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    inner class Decompose1AdjacentGoldenTrianglesArrangement: Decompose1() {
        // triangle A, PP, P
        val duplicatedSidesLength = decompose1NonAdjacentGoldenTrianglesArrangement.P.x - decompose1NonAdjacentGoldenTrianglesArrangement.symP.x

        val P = Point(
            x = decompose1NonAdjacentGoldenTrianglesArrangement.goldenTriangles[1].points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = decompose1NonAdjacentGoldenTrianglesArrangement.goldenTriangles[1].points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)

        )

        override val goldenTriangles = arrayOf(
            GoldenTriangle(points[2], decompose1NonAdjacentGoldenTrianglesArrangement.symP, points[1]),
            GoldenTriangle(points[2], P, decompose1NonAdjacentGoldenTrianglesArrangement.symP)
        )

        override val goldenGnomons = arrayOf(GoldenGnomon(P, points[0], decompose1NonAdjacentGoldenTrianglesArrangement.symP))

        override val decomposables: Array<Decomposable>
            get() = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    inner class DecomposeStep2 {

        // Sym of step1 arrange P
        private val step1arrangePSym = Symmetry.symmetryByVerticalAxis(0.0, decomposeStep1AdjacentGoldenTrianglesArrangement.P)
        private val goldenTriangle = GoldenTriangle(points[0], step1arrangePSym, decomposeStep1AdjacentGoldenTrianglesArrangement.P)
        private val goldenGnomon = GoldenGnomon(step1arrangePSym, decompose1NonAdjacentGoldenTrianglesArrangement.symP, decomposeStep1AdjacentGoldenTrianglesArrangement.P)

        private val baseGoldenTriangle = GoldenTriangle(
            decompose1NonAdjacentGoldenTrianglesArrangement.symP,
            points[1],
            Point(
                x = points[1].x + decomposeStep1AdjacentGoldenTrianglesArrangement.P.x - step1arrangePSym.x,
                y = points[1].y
            )
        )
        private val baseGoldeGnomon = GoldenGnomon(
            baseGoldenTriangle.points[2],
            points[2],
            decompose1NonAdjacentGoldenTrianglesArrangement.symP
        )
        private val otherGoldenTriangle = GoldenTriangle(
            decompose1NonAdjacentGoldenTrianglesArrangement.symP,
            decomposeInit.P,
            decomposeStep1AdjacentGoldenTrianglesArrangement.P
        )
        private val otherGoldenGnomon = GoldenGnomon(
            decomposeInit.P,
            decompose1NonAdjacentGoldenTrianglesArrangement.symP,
            points[2]
        )

        // Kite and Dart from losange
        private val P = Point(
            x = 0.0,
            y = otherGoldenTriangle.points[2].y - otherGoldenTriangle.points[1].y)
        private val goldenGnomon1 = GoldenGnomon(
            P,
            otherGoldenTriangle.points[1],
            otherGoldenTriangle.points[0]
        )

        private val goldenGnomon2 = GoldenGnomon(
            P,
            baseGoldenTriangle.points[0],
            baseGoldenTriangle.points[2]
        )

        private val goldenTriangle1 = GoldenTriangle(points[2], goldenGnomon1.points[1], goldenGnomon1.points[0])
        private val goldenTriangle2 = GoldenTriangle(points[2], goldenGnomon2.points[0], goldenGnomon2.points[2])

        private val goldenTriangles = arrayOf(goldenTriangle, baseGoldenTriangle, otherGoldenTriangle, goldenTriangle1, goldenTriangle2)
        private val goldenGnomons = arrayOf(goldenGnomon, baseGoldeGnomon, otherGoldenGnomon, goldenGnomon1, goldenGnomon2)
        val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)
        }

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
