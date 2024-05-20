package com.cjanie.pavages.tools

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class CheckPointInBounds {
    companion object {
        fun isInPointWithinGoldenTriangle(goldenTriangle: GoldenTriangle, point: Point): Boolean {
            // Case point under
            if(point.y < goldenTriangle.points[2].y) return false

            // point.x should be smaller than the one of point at the same level (y) on the right side of the triangle
            // define the point at the same level on the duplicated sides of the triangle; its x is the limit
            // we want to define x
            // use Trigonometry with right triangle, right angle in y = 0
            // we have the lenght of the opposite side to C
            val oppositeSideLength = point.y
            val adjacentSideLength = Trigonometry.adjacentSideLengthFromOppositeSideAndAngle(oppositeSideLength, GoldenTriangle.angleAtBaseDegrees)
            val xLimit = goldenTriangle.points[2].x - adjacentSideLength
            return point.x in -xLimit..xLimit
        }

    }

}