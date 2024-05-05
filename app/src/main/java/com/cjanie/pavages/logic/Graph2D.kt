package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.GoldenTriangleFactory
import com.cjanie.pavages.logic.triangles.IsoscelesTriangle
import com.cjanie.pavages.tools.Trigonometry

class Graph2D(size: Double = 200.0) {

    companion object {
        // Center
        private val O = Point("O", 0.0, 0.0)

    }

    val verticalAxis = arrayOf(
        Point(
            name = "A",
            x = O.x,
            y = O.y + size / 2
        ),
        Point(
            name = "A'",
            x = O.x,
            y = O.y - size / 2
        )
    )

    val horizontalAxis = arrayOf(
        Point(
            name = "B",
            x = O.x + size / 2,
            y = O.y
        ),
        Point(
            name = "B'",
            x = O.x - size / 2,
            y = O.y
        )
    )

    val scale = 200.0
    val P = Point(x = O.x - 200, y = 100.0)

    val triangle = GoldenTriangleFactory.create(scale)

}