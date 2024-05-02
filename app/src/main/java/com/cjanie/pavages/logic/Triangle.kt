package com.cjanie.pavages.logic

open class Triangle(val points: Array<Point>) {

    init {
        assert(points.size == 3)
    }
}