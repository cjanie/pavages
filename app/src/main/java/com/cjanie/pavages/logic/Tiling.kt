package com.cjanie.pavages.logic

open class Tiling(private val n: Int) {

    // an n-sided polygon is the motive of the tiling
    // a polygon has at min 3 sides
    companion object {
        const val POLYGON_MIN_NUMBER_OF_SIDES = 3
    }

    init {
        assert(n >= POLYGON_MIN_NUMBER_OF_SIDES)
    }

    // k is the number of polygons that are necessary to fill the plan around a summit A of a polygon

    val k: Int by lazy { numberOfPolygonsAroundAPolygonSummit() }

    private fun numberOfPolygonsAroundAPolygonSummit(): Int {
        /*
            Pavage of polygons of n sides
            2 adjacent sides of polygons form an angle a of 180 - 360/n ° = pi - 2pi/n rad
            in fact, lets cut it in n-2 triangles:
            the sum of the n angles of this polygon of n sides is (n-2) * 180

            To fill the plan around the summit of angle a
            k * a = 360 °
            k * (180 - 360/n) = 360 °
         */
        return (2 * n) / (n - 2)
    }
}