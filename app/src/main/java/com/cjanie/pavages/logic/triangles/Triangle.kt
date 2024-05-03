package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Point

open class Triangle(val points: Array<Point>) {

    init {
        assert(points.size == 3)
    }
}