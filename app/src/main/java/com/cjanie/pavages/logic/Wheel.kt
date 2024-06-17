package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Wheel {
    // A at the center of the wheel
    val A = Point("A", 0.0, 0.0)
    val duplicatedSideLength = 2.0


    val B = Point("B", 0.0, duplicatedSideLength)

    val triangles = mutableListOf<GoldenTriangle>();

    init {
        var i = 1
        var B = this.B
        while(i in 1..10) {
            val goldenTriangle = GoldenTriangle.createFromDuplicatedSidePoints(A, B)

            i++
        }
    }
}