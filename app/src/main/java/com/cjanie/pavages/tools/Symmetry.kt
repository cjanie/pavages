package com.cjanie.pavages.tools

import com.cjanie.pavages.logic.Point
import kotlin.math.abs

class Symmetry {

    companion object {

        fun symmetryByVerticalAxis(axisX: Double, point: Point, symPointName: String = "${point.name}'"): Point {
            return if(axisX < 0) Point(symPointName, axisX - (point.x + abs(axisX)), point.y)
            else  Point(symPointName, axisX - (point.x - axisX), point.y)
        }
        fun symmetryByHorizontalAxis(axisY: Double, point: Point, symPointName: String = "${point.name}'"): Point {
            return if(axisY < 0) Point(symPointName, point.x, axisY - (abs(axisY) + point.y))
            else Point(symPointName, point.x, axisY - (point.y - axisY))

        }

        fun symmetryByHorizontalAxis(axisY: Double, points: Array<Point>): Array<Point> {
            val symmetricPoints = Array(points.size) {
                symmetryByHorizontalAxis(axisY, points[it])
            }
            return symmetricPoints
        }

    }
}