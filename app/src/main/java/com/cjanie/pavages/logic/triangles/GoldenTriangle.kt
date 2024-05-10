package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Dart_atBottom
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Dart_atBottom_sym
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Kite_atBottom
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Kite_atBottom_sym
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

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
        //assert(decimal == phi)//((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
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

    // Decompose losange kite/Dart
    // Golden triangle P3 P6 symP1
    val BP3length = abs(symP2.x) + abs(P2.x)
    val P6 = Point(
        x = symP1.x + BP3length,
        y = symP1.y
    )
    // Golden triangle P6 P3 P7
    val P7 = Point(
        x = points[2].x - BP3length,
        y = points[2].y
    )
    // Golden triangle P2 P6 P2sym
    /*
    val duplicatedSidesLengthP2P6symP2 = P2.x - symP2.x
    val P6 = Point(
        x = P2.x - Trigonometry.adjacentSideLength(duplicatedSidesLengthP2P6symP2, 36.0),
        y = P2.y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLengthP2P6symP2, 36.0)
    )

     */


    fun iterate(iteration: Int, arrange: Boolean = false): Array<Decomposable> {
        if(iteration > 0) {
            if(iteration == 3) {
                val goldenTriangleToDecompose = GoldenTriangle(points[0], symP1, P1)
                // Golden triangle AA P1 symP1
                // Height of A symP1 P1 = A.y - symP1.y
                val AA = Point(
                    x = points[0].x,
                    y = symP1.y - (points[0].y - symP1.y)
                )

                val goldenTriangle2ToDecompose = GoldenTriangle(AA, P1, symP1)

                val decomposablesTop = GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle2ToDecompose).decomposables

                // remove pyramidion
                val top = decomposablesTop.toMutableList()
                    top.removeAt(5)

                val bottomGoldetriangleToDecompose = GoldenTriangle(symP1, points[1], P3)

                // GoldenTrinagle P1 P7 C
                val goldenTriangleP1P7C = GoldenTriangle(P1, P7, points[2])

                if(arrange) {
                    val inversed = GoldenTriangleDecomposables_Dart_atBottom_sym(goldenTriangle2ToDecompose).decomposables.toMutableList()
                    inversed.removeAt(5)
                    return arrayOf(
                        *GoldenTriangleDecomposables_Dart_atBottom(goldenTriangleToDecompose, iteration).decomposables,
                        *inversed.toTypedArray(),
                        *GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(bottomGoldetriangleToDecompose).decomposables,
                        *GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(goldenTriangleP1P7C).decomposables
                        )

                }
                val decomposables = arrayOf(
                    // Top triangle decomposition
                    *GoldenTriangleDecomposables_Kite_atBottom(goldenTriangleToDecompose).decomposables,
                    // sym AA
                    *top.toTypedArray(),
                    // Bottom triangle decomposition
                    *GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(bottomGoldetriangleToDecompose).decomposables,
                    // Losange decomposition
                    //*GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon_sym(losangeGoldenTriangle).decomposables,
                    //*GoldenTriangleDecomposables_2AdajacentTriangles_1Gnomon(losangeGoldenTriangle2).decomposables,
                    *GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon_sym(goldenTriangleP1P7C).decomposables,
                    //*GoldenTriangleDecomposables_Kite_atBottom_sym(goldenTriangle2ToDecompose).decomposables
                )
                return  decomposables
            }
            if(iteration == 4)

                return arrayOf(
                    *GoldenTriangleDecomposables_Kite_atBottom(GoldenTriangle(points[0], symP2, P2)).decomposables
                )
            if(iteration % 2 != 0) return getOneToThree(arrange)
            else return getUpToKiteAndDart(iteration, arrange)
        }
        return arrayOf(this)
    }

    private fun getOneToThree(arrange: Boolean): Array<Decomposable> {
        return if (arrange) GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
            goldenTriangle = this
        ).decomposables
        else GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
            goldenTriangle = this
        ).decomposables
    }
    private fun getUpToKiteAndDart(iteration: Int, arrange: Boolean): Array<Decomposable> {
        return if (arrange) GoldenTriangleDecomposables_Dart_atBottom(this, iteration).decomposables
        else GoldenTriangleDecomposables_Kite_atBottom(this).decomposables
    }
}