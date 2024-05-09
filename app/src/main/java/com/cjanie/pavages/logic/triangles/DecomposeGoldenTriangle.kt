package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry

class DecomposeGoldenTriangle(val goldenTriangle: GoldenTriangle) {

    fun decomposePacket(
        iteration: Int, arrange: Boolean
    ): Array<Decomposable> {
        if(iteration > 0) {
            if(iteration % 2 != 0) return getOneToThree(arrange)
            else return getUpToKiteAndDart(arrange)
        }
        return arrayOf(goldenTriangle)
    }

    private fun getOneToThree(arrange: Boolean): Array<Decomposable> {
        return if (arrange) DecomposePacket1AdjacentGoldenTrianglesArrangement().decomposables
        else DecomposePacket1NonAdjacentGoldenTrianglesArrangement().decomposables
    }

    private fun getUpToKiteAndDart(arrange: Boolean): Array<Decomposable> {
        return if (arrange) DecomposePacket2DartBottomArrangement().decomposables
        else DecomposePacket2KiteBottomArrangement().decomposables
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
        val duplicatedSidesLength = goldenTriangle.points[2].x - goldenTriangle.points[1].x

        // P1 coordinates
        val P1 = Point(
            x = goldenTriangle.points[1].x + Trigonometry.adjacentSideLength(duplicatedSidesLength, 36.0),
            y = goldenTriangle.points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)
        )

        // Sym P1
        val symP1 = Symmetry.symmetryByVerticalAxis(goldenTriangle.points[0].x, P1)

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

            val symP2 = Symmetry.symmetryByVerticalAxis(goldenTriangle.points[0].x, P2)
            val P3 = Point(
                x = goldenTriangle.points[1].x + P2.x - symP2.x,
                y = goldenTriangle.points[1].y
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
            GoldenTriangle(goldenTriangle.points[1], goldenTriangle.points[2], pointsToDecompose[0]),
            // Triangle A symP1 P1 of base symP1 P1
            GoldenTriangle(goldenTriangle.points[0], pointsToDecompose[1], pointsToDecompose[0])
        )
        // + 1 Gnomon symP1 B P1 of base B P1
        override val goldenGnomons = arrayOf(GoldenGnomon(pointsToDecompose[1], goldenTriangle.points[1], pointsToDecompose[0]))

        override val decomposables: Array<Decomposable> = arrayOf(*goldenTriangles, *goldenGnomons)

        init {
            assertion()
        }
    }

    inner class DecomposePacket1AdjacentGoldenTrianglesArrangement: DecomposePacket1() {

        override val goldenTriangles = arrayOf(
            // C symP1 B: base = symP1 B
            GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[1], goldenTriangle.points[1]),
            // C P2 symP1: base = P2 symP1
            GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[2], pointsToDecompose[1])
        )

        override val goldenGnomons = arrayOf(GoldenGnomon(pointsToDecompose[2], goldenTriangle.points[0], pointsToDecompose[1]))

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
                goldenTriangle.points[1], // B
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
            GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[0], pointsToDecompose[5]),
            // C P4 P3
            GoldenTriangle(goldenTriangle.points[2], pointsToDecompose[5], pointsToDecompose[4])
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
            return GoldenTriangle(goldenTriangle.points[0], pointsToDecompose[3], pointsToDecompose[2])
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
            GoldenGnomon(pointsToDecompose[6], pointsToDecompose[4], goldenTriangle.points[2]),
            // P5 C P1
            GoldenGnomon(pointsToDecompose[6], goldenTriangle.points[2], pointsToDecompose[0])
        )

        override fun topGoldenTriangle(): GoldenTriangle {
            // symP1 P2 P6
            return GoldenTriangle(pointsToDecompose[1], pointsToDecompose[2], pointsToDecompose[7])
        }

        override fun topGoldenGnomon(): GoldenGnomon {
            // P6 P2 A
            return GoldenGnomon(pointsToDecompose[7], pointsToDecompose[2], goldenTriangle.points[0])
        }

    }


}