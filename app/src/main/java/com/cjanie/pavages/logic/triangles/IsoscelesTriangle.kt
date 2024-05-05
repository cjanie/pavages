package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Line
import com.cjanie.pavages.logic.Point

open class IsoscelesTriangle(val oppositeToBase: Point, val basePoint1: Point, val basePoint2: Point): Triangle(
    arrayOf(oppositeToBase, basePoint1, basePoint2)) {

    private val duplicatedSides = arrayOf(Line(oppositeToBase, basePoint1), Line(oppositeToBase, basePoint2))

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