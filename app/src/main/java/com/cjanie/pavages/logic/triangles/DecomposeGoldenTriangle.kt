package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposablesDartAtBottom
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdajacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Kite_atBottom
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
        return if (arrange) GoldenTriangleDecomposablesDartAtBottom(goldenTriangle).decomposables
        else GoldenTriangleDecomposables_Kite_atBottom(goldenTriangle).decomposables
    }

    abstract inner class DecomposePacket {
        abstract val decomposables: Array<Decomposable>
        protected fun setUpDecomposables(goldenTriangles: Array<GoldenTriangle>, goldenGnomons: Array<GoldenGnomon>): Array<Decomposable> {
            return arrayOf(*goldenTriangles, *goldenGnomons)
        }
    }

    abstract inner class DecomposePacket1: DecomposePacket() {
        // total : 3 = 2 golden triangle + 1 golden gnomon
        val pointsToDecompose = getPointsToDecompose(1)
        protected fun assert_3_Decomposables() {
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

        private val goldenTriangleDecomposables
        = GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
            goldenTriangle = goldenTriangle
        )

        private val goldenTriangles = goldenTriangleDecomposables.goldenTriangles
        private val goldenGnomons = arrayOf(goldenTriangleDecomposables.goldenGnomon)

        override val decomposables: Array<Decomposable> = setUpDecomposables(goldenTriangles, goldenGnomons)
        init {
            assert_3_Decomposables()
        }
    }

    inner class DecomposePacket1AdjacentGoldenTrianglesArrangement: DecomposePacket1() {

        private val goldenTriangleDecomposables = GoldenTriangleDecomposables_2AdajacentTriangles_1Gnomon(
            goldenTriangle = goldenTriangle
        )
        private val goldenTriangles = goldenTriangleDecomposables.goldenTriangles
        private val goldenGnomons = arrayOf(goldenTriangleDecomposables.goldenGnomon)

        override val decomposables: Array<Decomposable> = setUpDecomposables(goldenTriangles, goldenGnomons)

        init {
            assert_3_Decomposables()
        }


    }




}