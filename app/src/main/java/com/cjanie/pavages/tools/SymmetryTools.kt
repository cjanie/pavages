package com.cjanie.pavages.tools

import com.cjanie.pavages.Point
import com.cjanie.pavages.exceptions.NullPointException

class SymmetryTools {

    companion object {

        fun symmetryByVerticalAxis(axisX: Double, point: Point, symPointName: String = "${point.name}'"): Point {
            return Point(symPointName, axisX - point.x, point.y)
        }
        fun symmetryByHorizontalAxis(axisY: Double, point: Point, symPointName: String = "${point.name}'"): Point {
            return Point(symPointName, point.x, axisY - point.y)
        }

        fun symmetryByHorizontalAxis(axisY: Double, points: Array<Point>): Array<Point> {
            val symmetricPoints = Array(points.size) {
                symmetryByHorizontalAxis(axisY, points[it])
            }
            return symmetricPoints
        }

    }
}