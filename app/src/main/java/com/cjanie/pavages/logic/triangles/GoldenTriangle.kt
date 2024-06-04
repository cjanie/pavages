package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposableModel
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

    init {

        // check that the duplicated side is in the golden ration phi to the base side
        val decimal = BigDecimal(duplicatedSideLength() / baseSideLength()).setScale(1, RoundingMode.DOWN)
        println("${duplicatedSideLength() / baseSideLength()}!!!!!!!!!!!!!!!!!!")
        val phi = BigDecimal(Number.GOLDEN_NUMBER_PHI).setScale(1, RoundingMode.DOWN)
        println("${Number.GOLDEN_NUMBER_PHI}!!!!!!!!!!!!!!!!!!")
        assert(decimal == phi)//((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
    }

    companion object {
        val goldenRatio = Number.GOLDEN_NUMBER_PHI
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
            // Get height by trigonometry
            val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
                duplicatedSidesLength,
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

        fun createByHeight(height: Double): GoldenTriangle {
            // Golden ratio
            // From half angle A
            val hypothenuse = Trigonometry.hypotenuseLengthFromAdjacentSideAndAngle(height, 36.0 / 2.0)

            // From angle = B = 72° = angleAtBaseDegrees
            val adjacent = Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(hypothenuse, angleAtBaseDegrees)
            val baseLength = adjacent * 2
            create(baseLength)

            // Set points coordinates
            // A, opposite to base, on vertical Axis
            val A = Point(x = 0.0, y = height)
            // Base BC on horizontal Axis
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)
        }

    }

    val A = points[0]
    val B = points[1]
    val C = points[2]

    override fun decompose(): Array<Decomposable> {

        val (pX, pY) = A.complex + (B.complex - A.complex) / goldenRatio
        val P = Point("P", pX, pY)

        // 1 triangle + 1 gnomon
        // C P B
        return arrayOf(
            GoldenTriangle(C, P, B),
            GoldenGnomon(P, C, A)
        )
    }

    // 2 triangles + 1 gnomon
    override fun decompose(arrange: Boolean): Array<Decomposable> {
        val (pX, pY) = A.complex + (B.complex - A.complex) / goldenRatio
        val P = Point("P", pX, pY)

        if (arrange) {
            return arrayOf(
                GoldenTriangle(C, P, B),
                *GoldenGnomon(P, C, A).decompose(arrange)
            )
        }

        val (sympX, sympY) = A.complex + (C.complex - A.complex) / goldenRatio
        val symP = Point("P'", sympX, sympY)

        return arrayOf(
            GoldenTriangle(C, P, B),
            GoldenTriangle(A, symP, P),
            GoldenGnomon(symP, C, P)
        )
    }

    // iteration 1 returns P1, symP1, P2
    // Golden triangle BCP : base = CP, duplicated sides = BC and BP
    val duplicatedSidesLength = points[2].x - points[1].x

    // P1 coordinates
    val P1 = Point(
        x = points[1].x + Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0),
        y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)
    )



    // Sym P1
    val symP1 = Symmetry.symmetryByVerticalAxis(points[0].x, P1)

    // triangle symP1, P1, P2: base = P1 P2, duplicated sides = symP1 P1 and sym P1 P2
    val duplicatedSideSymP1_P1Length = P1.x - symP1.x
    // P2 coordinates
    val P2 = Point(
        x = symP1.x
                + Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(duplicatedSideSymP1_P1Length, 36.0),
        y = symP1.y
                + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
            duplicatedSideSymP1_P1Length, 36.0
        )
    )

    val symP2 = Symmetry.symmetryByVerticalAxis(points[0].x, P2)
    val P3 = Point(
        x = points[1].x + P2.x - symP2.x,
        y = points[1].y
    )
    val symP3 = Symmetry.symmetryByVerticalAxis(points[0].x, P3)


    // Kite and Dart from losange P3 C P1 symP1
    val P4 = Point(
        x = points[0].x,
        y = points[2].y + P2.y - P1.y
    )

    // P5 Sym of P2 on horizontal axis P1 symP1
    // val P5 = Symmetry.symmetryByHorizontalAxis(P1.y, P2)
    val P5 = Point(
        x = P2.x,
        y = P1.y - (P2.y - P1.y)
    )

    val symP5 = Symmetry.symmetryByVerticalAxis(points[0].x, P5)

    fun iterate(iteration: Int, model: DecomposableModel): Array<Decomposable> {
        when (model) {
            DecomposableModel.NON_ADJACENT_TRIANGLE_2_GNOMON_1 -> return iterateDecompose2NonAdjacentTriangles1Gnomon(iteration)
            DecomposableModel.ADJACENT_TRIANGLE_2_GNOMON_1 -> return iterateDecompose2NonAdjacentTriangles1Gnomon(iteration, true)
            else -> return iterate(iteration)
        }
    }

    fun iterateDecompose2NonAdjacentTriangles1Gnomon(iteration: Int, arrange: Boolean = false): Array<Decomposable> {
        var i = 0
        val decompose = mutableListOf<Decomposable>(this)
        if (iteration == 0) return decompose.toTypedArray()

        while (i < iteration) {
            val decomposePrepare = mutableListOf<Decomposable>()
            for (d in decompose) {
                decomposePrepare.addAll(d.decompose())
            }
            decompose.clear()
            decompose.addAll(decomposePrepare)
            i++
        }

        val updatedList = mutableListOf<Decomposable>()
        for (d in decompose) {
            if (d is GoldenTriangle) updatedList.add(d)
            if (d is GoldenGnomon) {
                if (arrange) {
                    updatedList.addAll(d.decompose(GoldenGnomon.DecompositionModel.ONE_TRIANGLE_ONE_GNOMON))
                } else {
                    updatedList.addAll(d.decompose(GoldenGnomon.DecompositionModel.ONE_TRIANGLE_ONE_GNOMON_SYM))
                }
            }

        }
        return updatedList.toTypedArray()

    }

    fun iterate(iteration: Int): Array<Decomposable> {
        var i = 0
        val decompose = mutableListOf<Decomposable>(this)
        if(iteration == 0) return decompose.toTypedArray()

        while (i  < iteration) {
            val decomposePrepare = mutableListOf<Decomposable>()
            for (d in decompose) {
                decomposePrepare.addAll(d.decompose())
            }
            decompose.clear()
            decompose.addAll(decomposePrepare)
            i++
        }

        val updatedList = mutableListOf<Decomposable>()
        for (d in decompose) {
            updatedList.addAll(d.decompose())
        }
        return updatedList.toTypedArray()
    }

    }