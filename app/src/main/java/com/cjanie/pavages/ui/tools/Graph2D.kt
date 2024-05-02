package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import com.cjanie.pavages.logic.Point

class Graph2D(private val verticalAxisX: Double, private val horizontalAxisY: Double) {

    fun pointToOffset(point: Point): Offset {
        return Offset((verticalAxisX + point.x).toFloat(), (horizontalAxisY + point.y).toFloat())
    }

    fun pointsToOffsets(points: Array<Point>): Array<Offset> {
        val offsets = Array(points.size) {
            pointToOffset(points[it])
        }
        return offsets
    }

}