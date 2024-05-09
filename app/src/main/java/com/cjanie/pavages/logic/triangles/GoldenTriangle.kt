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

    val lastIterationPacket = mutableListOf<Decomposable>()
    fun iterate(iteration: Int, arrange: Boolean = false): Array<Decomposable> {

        val decomposeGoldenTriangle = DecomposeGoldenTriangle(this)
        val packet = decomposeGoldenTriangle.decomposePacket(iteration, arrange)
        lastIterationPacket.addAll(packet)

        if(iteration in 1..2) {
            return lastIterationPacket.toTypedArray()

        } else if (iteration == 3) {
            // filter the golden triangles of last iteration which base is horizontal
            val goldenTriangles = lastIterationPacket.filter {
                it is GoldenTriangle && it.basePoint1.y == it.basePoint2.y
            }
            val decomposables = mutableListOf<Decomposable>()
            for (goldenTriangle in goldenTriangles) {
                // Create new DecomposeGoldenTriangle
                val decomposeGoldenTriangle = DecomposeGoldenTriangle(goldenTriangle as GoldenTriangle)
                // 3 : iterate // oneToThree()
                // 4 Iterate again // uptoKiteAndDart()
                val packet = decomposeGoldenTriangle.decomposePacket(iteration, arrange)
                decomposables.addAll(packet)
            }
            lastIterationPacket.clear()
            lastIterationPacket.addAll(decomposables)
            return lastIterationPacket.toTypedArray()
            //return goldenTriangles.toTypedArray()

        }



            // Than again for all the golden triangles of last iteration...
            // 5 :  Create new DecomposeGoldenTriangle and iterate // OneToThree()
            // 6 : Iterate again // upToKiteAndDart
        //}

        // For more use the iterate on new golden triangles
        // Default return the initial golden triangle ABC
        return arrayOf(this)
    }

    fun iterate(goldenTriangle: DecomposeGoldenTriangle, iteration: Int, arrange: Boolean) {

    }

}