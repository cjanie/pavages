package com.cjanie.pavages.tools

import com.cjanie.pavages.logic.Point
import kotlin.math.abs

class Symmetry {

    companion object {

        fun symmetryByVerticalAxis(axisX: Double, point: Point, symPointName: String = "${point.name}'"): Point {
            if(axisX < 0) {
                return Point(symPointName, axisX - (point.x+ abs(axisX)), point.y)
            }
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