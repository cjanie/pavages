package com.cjanie.pavages.tools

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class Trigonometry {

    companion object {
        private fun degreeToRadian(angleInDegrees: Double): Double {
            return angleInDegrees * Math.PI / 180.0
        }

         fun radianToDegree(angleInRadian: Double): Double {
            // angleInRadian × 180°/π
            return angleInRadian * 180.0 / Math.PI
        }

        fun cosine(angleInDegrees: Double): Double {
            return cos(degreeToRadian(angleInDegrees))
        }

        fun sine(angleInDegrees: Double): Double {
            return sin(degreeToRadian(angleInDegrees))
        }

        private fun tangente(angleInDegrees: Double): Double {
            return tan(degreeToRadian(angleInDegrees))
        }

        fun adjacentSideLengthFromHypotenuseAndAngle(hypotenuseLength: Double, angleInDegrees: Double): Double {
            return hypotenuseLength * cosine(angleInDegrees)
        }

        fun adjacentSideLengthFromOppositeSideAndAngle(oppositeSideLength: Double, angleInDegrees: Double): Double {
            return oppositeSideLength / tangente(angleInDegrees)
        }

        fun oppositeSideLengthFromHypotenuseAndAngle(hypotenuseLength: Double, angleInDegrees: Double): Double {
            return hypotenuseLength * sine(angleInDegrees)
        }

        fun oppositeSideLengthFromAdjacentSideAndAngle(adjacentSideLength: Double, angleInDegrees: Double): Double {
            return adjacentSideLength * tangente(angleInDegrees)
        }
        fun hypotenuseLengthFromAdjacentSideAndAngle(adjacentSideLength: Double, angleInDegrees: Double): Double {
            return adjacentSideLength / cosine(angleInDegrees)
        }

        fun hypotenuseLengthFromPythagoreanTheorem(adjacentSideLength: Double, oppositeSideLength: Double): Double {
            // Si un triangle est rectangle,
            // alors le carré de la logueur de l'hypoténuse est égal à
            // la somme des carrés des longueurs des deux autres côtés
            return sqrt(adjacentSideLength.pow(2) + oppositeSideLength.pow(2))
        }

        fun side2LengthFromPythagoreTheorem(hypotenuseLength: Double, side1Length: Double): Double {
            return sqrt(hypotenuseLength.pow(2) - side1Length.pow(2))
        }

        fun angleDegreesFromHypothenuseAndAdjacentLengths(hypothenuseLength: Double, adjacentLength: Double): Double {
            val cosOfAngle = adjacentLength / hypothenuseLength
            return radianToDegree(acos(cosOfAngle))
        }

        fun angleDegreesFromHypothenuseAndOppositeLengths(hypothenuseLength: Double, oppositeLength: Double): Double {
            val sinOfAngle = oppositeLength / hypothenuseLength
            return radianToDegree(asin(sinOfAngle))
        }

        fun angleDegreesFromOppositeAndAdjacentLengths(oppositeLength: Double, adjacentLength: Double): Double {
            val tanOfAngle = oppositeLength / adjacentLength
            return radianToDegree(atan(tanOfAngle))
        }


    }

}