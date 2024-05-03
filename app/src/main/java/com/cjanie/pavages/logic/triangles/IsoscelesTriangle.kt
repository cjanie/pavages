package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Line
import com.cjanie.pavages.logic.Point

open class IsoscelesTriangle(pointOppositeToBase: Point, basePoint1: Point, basePoint2: Point): Triangle(
    arrayOf(pointOppositeToBase, basePoint1, basePoint2)) {

    private val duplicatedSides = arrayOf(Line(pointOppositeToBase, basePoint1), Line(pointOppositeToBase, basePoint2))

    private val baseSide = Line(basePoint1, basePoint2)

    init {
        assert(duplicatedSides[0].length() == duplicatedSides[1].length())
    }

    fun duplicatedSideLength(): Double {
        return duplicatedSides[0].length()
    }

    fun baseSideLength(): Double {
        return baseSide.length()
    }
}