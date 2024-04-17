package com.cjanie.pavages.tools

import com.cjanie.pavages.Point

class SymmetryTools {

    companion object {
        private fun symmetryByHorizontalAxis(axisY: Double, point: Point): Point {
            return Point(point.x, axisY - point.y)
        }

        fun symmetryByHorizontalAxis(axisY: Double, points: Array<Point?>): Array<Point?> {
            val symetricPoints = arrayOfNulls<Point>(points.size)
            if(points.isNotEmpty()) {
                for (i in points.indices) {
                    symetricPoints[i] = symmetryByHorizontalAxis(axisY, points[i]!!)
                }
            }
            return symetricPoints
        }
    }
}