package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Point

open class Triangle(val points: Array<Point>) {

    companion object {
        val ANGLES_DEGREES_SUM = 180.0
    }

    init {
        assert(points.size == 3)
    }
}