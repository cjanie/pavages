package com.cjanie.pavages.tools

import com.cjanie.pavages.Point
import com.cjanie.pavages.exceptions.NullPointException

class SymmetryTools {

    companion object {
        private fun symmetryByHorizontalAxis(axisY: Double, point: Point): Point {
            return Point(point.x, axisY - point.y)
        }

        fun symmetryByHorizontalAxis(axisY: Double, points: Array<Point>): Array<Point> {
            val symmetricPoints = Array(points.size) {
                symmetryByHorizontalAxis(axisY, points[it])
            }
            return symmetricPoints
        }

    }
}