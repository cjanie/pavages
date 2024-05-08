package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry
import java.math.BigDecimal
import java.math.RoundingMode

class GoldenTriangle(
    oppositeToBase: Point = Point(name = "A", x = 0.0, y = height),
    basePoint1: Point = Point(name = "B", x = -baseRatio / 2.0, y = 0.0),
    basePoint2: Point = Point(name = "C", x = baseRatio / 2.0, y = 0.0)
): IsoscelesTriangle(oppositeToBase, basePoint1, basePoint2),
    Decomposable {


    val decompose1NonAdjacentGoldenTrianglesArrangement by lazy { DecomposePacket1NonAdjacentGoldenTrianglesArrangement() }
    val decomposeStep1AdjacentGoldenTrianglesArrangement by lazy { DecomposePacket1AdjacentGoldenTrianglesArrangement() }
    val decompose2KiteBottomArrangement by lazy { DecomposePacket2KiteBottomArrangement() }
    val decompose2DartBottomArrangement by lazy { DecomposePacket2DartBottomArrangement()}


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
        val pointsToDecompose = getPointsToDecompose(iteration)
        if(iteration == 1) {
            if(arrange) {
                // Case adjacent golden triangle arrangement
                return decomposeStep1AdjacentGoldenTrianglesArrangement.decomposables
            }
            // Case non adjacent golden triangle arrangement
            return decompose1NonAdjacentGoldenTrianglesArrangement.decomposables
        } else if (iteration == 2) {
            if(arrange) {
                return decompose2DartBottomArrangement.decomposables
            }
            return decompose2KiteBottomArrangement.decomposables
        }

        // Default return the initial golden triangle ABC
        return arrayOf(this)
    }

    abstract inner class DecomposePacket {
        abstract val goldenTriangles: Array<GoldenTriangle>
        abstract val goldenGnomons: Array<GoldenGnomon>
        abstract val decomposables: Array<Decomposable>
    }

    abstract inner class DecomposePacket1: DecomposePacket() {
        // total : 3 = 2 golden triangle + 1 golden gnomon
        val pointsToDecompose = getPointsToDecompose(1)
        protected fun assertion() {
            assert(goldenTriangles.size == 2)
            assert(goldenGnomons.size == 1)
            assert(decomposables.size == 3)
        }

    }

    fun getPointsToDecompose(iteration: Int): Array<Point> {
        // iteration 1 returns P1, symP1, P2
        // Golden triangle BCP : base = CP, duplicated sides = BC and BP
        val duplicatedSidesLength = points[2].x - points[1].x

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

        // At first iteration: 3 points P1 as 3 decomposables
        val pointsToDecompose = arrayOf(P1, symP1, P2)

        if (iteration == 2) { // 8 points

            val symP2 = Symmetry.symmetryByVerticalAxis(points[0].x, P2)
            val P3 = Point(
                x = points[1].x + P2.x - symP2.x,
                y = points[1].y
            )

            // Kite and Dart from losange P3 C P1 symP1
            val P4 = Point(
                x = 0.0,
                y = P2.y - P1.y
            )

            // P5 Sym of P2 on horizontal axis P1 symP1
            // val P5 = Symmetry.symmetryByHorizontalAxis(P1.y, P2)
            val P5 = Point(
                x = P2.x,
                y = P1.y - (P2.y - P1.y)
            )

            // Golden triangle P2 P6 P2sym
            val duplicatedSidesLengthP2P6symP2 = P2.x - symP2.x
            val P6 = Point(
                x = P2.x - Trigonometry.adjacentSideLength(duplicatedSidesLengthP2P6symP2, 36.0),
                y = P2.y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLengthP2P6symP2, 36.0)
            )

            val newPoints = arrayOf(symP2, P3, P4, P5, P6)
            return pointsToDecompose + newPoints // Total 8 points as 8 decomposables
        }

        return pointsToDecompose
    }

    inner class DecomposePacket1NonAdjacentGoldenTrianglesArrangement: DecomposePacket1() {
        // Total 2 goldenTriangles
        override val goldenTriangles = arrayOf(
            // BCP of base CP
            GoldenTriangle(points[1], points[2], pointsToDecompose[0]),
            // Triangle A symP1 P1 of base symP1 P1
            GoldenTriangle(points[0], pointsToDecompose[1], pointsToDecompose[0])
        )
        // + 1 Gnomon symP1 B P1 of base B P1
        override val goldenGnomons = arrayOf(GoldenGnomon(pointsToDecompose[1], points[1], pointsToDecompose[0]))

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    inner class DecomposePacket1AdjacentGoldenTrianglesArrangement: DecomposePacket1() {

        override val goldenTriangles = arrayOf(
            // C symP1 B: base = symP1 B
            GoldenTriangle(points[2], pointsToDecompose[1], points[1]),
            // C P2 symP1: base = P2 symP1
            GoldenTriangle(points[2], pointsToDecompose[2], pointsToDecompose[1])
        )

        override val goldenGnomons = arrayOf(GoldenGnomon(pointsToDecompose[2], points[0], pointsToDecompose[1]))

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    abstract inner class DecomposePacket2: DecomposePacket() {
        abstract fun kite(): Array<GoldenTriangle>
        abstract fun dart(): Array<GoldenGnomon>

        abstract fun topGoldenTriangle(): GoldenTriangle

        abstract fun topGoldenGnomon(): GoldenGnomon

        val pointsToDecompose = getPointsToDecompose(2)

        override val goldenTriangles = arrayOf(
            // Top
            topGoldenTriangle(),
            // base
            GoldenTriangle(
                pointsToDecompose[1], //symP1,
                points[1], // B
                pointsToDecompose[4] //P3
            ),
            // under top
            GoldenTriangle(
                pointsToDecompose[1], //symP1,
                pointsToDecompose[0], //P1,
                pointsToDecompose[2] //P2
            ),
            // Kite at bottom
            *kite()
        )

        override val goldenGnomons = arrayOf(
            topGoldenGnomon(),
            // Dart
            *dart()
        )

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)
        init {
            assert(goldenTriangles.size == 5)
            assert(goldenGnomons.size == 3)
            assert(decomposables.size == 8)
        }

    }
    inner class DecomposePacket2KiteBottomArrangement: DecomposePacket2() {

        // Kite at bottom
        override fun kite() = arrayOf(
            //C P1 P4
            GoldenTriangle(points[2], pointsToDecompose[0], pointsToDecompose[5]),
            // C P4 P3
            GoldenTriangle(points[2], pointsToDecompose[5], pointsToDecompose[4])
        )

        // Dart up
        override fun dart() = arrayOf(
            GoldenGnomon(
                pointsToDecompose[5], // P4,
                pointsToDecompose[0], // P1,
                pointsToDecompose[1] // symP1
            ),
            GoldenGnomon(
                pointsToDecompose[5], // P4,
                pointsToDecompose[1], // symP1,
                pointsToDecompose[4] // P3
            )
        )

        override fun topGoldenTriangle(): GoldenTriangle {
            // A symP2 P2
            return GoldenTriangle(points[0], pointsToDecompose[3], pointsToDecompose[2])
        }

        override fun topGoldenGnomon(): GoldenGnomon {
            // symP2 symP1 P2
            return GoldenGnomon(pointsToDecompose[3], pointsToDecompose[1], pointsToDecompose[2])
        }

    }

    inner class DecomposePacket2DartBottomArrangement: DecomposePacket2() {

        override fun kite() = arrayOf(
            // symP1 P5, P1
            GoldenTriangle(pointsToDecompose[1], pointsToDecompose[6], pointsToDecompose[0]),
            // symP1 P3 P5
            GoldenTriangle(pointsToDecompose[1], pointsToDecompose[4], pointsToDecompose[6])
        )

        override fun dart() = arrayOf(
            // P5 P3 C
            GoldenGnomon(pointsToDecompose[6], pointsToDecompose[4], points[2]),
            // P5 C P1
            GoldenGnomon(pointsToDecompose[6], points[2], pointsToDecompose[0])
        )

        override fun topGoldenTriangle(): GoldenTriangle {
            // symP1 P2 P6
            return GoldenTriangle(pointsToDecompose[1], pointsToDecompose[2], pointsToDecompose[7])
        }

        override fun topGoldenGnomon(): GoldenGnomon {
            // P6 P2 A
            return GoldenGnomon(pointsToDecompose[7], pointsToDecompose[2], points[0])
        }

    }

}