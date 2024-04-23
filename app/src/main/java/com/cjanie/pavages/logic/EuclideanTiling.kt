package com.cjanie.pavages.logic

class EuclideanTiling(n: Int): Tiling(n) {

    // An Euclidean plan is a plan without curve, a flat plan
    // n, the number of sides of the polygon motive, must be <= 7
    // for k, the number of polygons around a summit A, must be an integer

    init {
        assert(n <= 7)
    }

}