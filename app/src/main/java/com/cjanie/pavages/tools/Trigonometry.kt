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
            return angleInDegrees * Math.PI / 180
        }

        private fun cosine(angleInDegrees: Double): Double {
            return cos(degreeToRadian(angleInDegrees))
        }

        private fun sine(angleInDegrees: Double): Double {
            return sin(degreeToRadian(angleInDegrees))
        }

        private fun tangente(angleInDegrees: Double): Double {
            return tan(degreeToRadian(angleInDegrees))
        }

        fun adjacentSideLength(hypotenuseLength: Double, angleInDegrees: Double): Double {
            return hypotenuseLength * cosine(angleInDegrees)
        }

        fun oppositeSideLengthFromHypotenuseAndAngle(hypotenuseLength: Double, angleInDegrees: Double): Double {
            return hypotenuseLength * sine(angleInDegrees)
        }

        fun oppositeSideLengthFromAdjacentSideAndAngle(adjacentSideLength: Double, angleInDegrees: Double): Double {
            return adjacentSideLength * tangente(angleInDegrees)
        }
        fun hypotenuseLength(adjacentSideLength: Double, angleInDegrees: Double): Double {
            return adjacentSideLength / cosine(angleInDegrees)
        }

        fun hypotenuseLengthFromPythagoreanTheorem(side1Length: Double, side2Length: Double): Double {
            // Si un triangle est rectangle,
            // alors le carré de la logueur de l'hypoténuse est égal à
            // la somme des carrés des longueurs des deux autres côtés
            return sqrt(side1Length.pow(2) + side2Length.pow(2))
        }

        fun side2LengthFromPythagoreTheorem(hypotenuseLength: Double, side1Length: Double): Double {
            return sqrt(hypotenuseLength.pow(2) - side1Length.pow(2))
        }

        fun angleDegreesFromHypothenuseAndAdjacentLengths(hypothenuseLength: Double, adjacentLength: Double): Double {
            val cosOfAngle = adjacentLength / hypothenuseLength
            return acos(cosOfAngle)
        }

        fun angleDegreesFromHypothenuseAndOppositeLengths(hypothenuseLength: Double, oppositeLength: Double): Double {
            val sinOfAngle = oppositeLength / hypothenuseLength
            return asin(sinOfAngle)
        }

        fun angleDegreesFromOppositeAndAdjacentLengths(oppositeLength: Double, adjacentLength: Double): Double {
            val tanOfAngle = oppositeLength / adjacentLength
            return atan(tanOfAngle)
        }


    }

}