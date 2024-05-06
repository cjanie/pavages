package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import com.cjanie.pavages.logic.Graph2D
import com.cjanie.pavages.logic.Point

class CanvasGraph2DAdapter(canvasSize: Float) {

    // Vertical Axis
    val verticalAxis = arrayOf(
            Offset(
                canvasSize / 2f,
                0f),
            Offset(
                canvasSize / 2f,
                canvasSize)
        )

    // Horizontal Axis
    val horizontalAxis = arrayOf(
        Offset(
           canvasSize,
            canvasSize / 2f,
        ),
        Offset(
            0f,
            canvasSize / 2f
        )
    )

    // Center
    val center = Offset(
        canvasSize / 2f,
        canvasSize / 2f
    )

    // Graph 2D containing shapes
    private val graph2D = Graph2D()

    private val goldenTrianglePoints = graph2D.goldenTriangle.points

    // Path for shape
    val goldenTrianglePath = DrawTools.createPath(arrayOf(
        offset(goldenTrianglePoints[0]),
        offset(goldenTrianglePoints[1]),
        offset(goldenTrianglePoints[2])))

    private fun offset(point: Point): Offset {
        return Offset(
            center.x + point.x.toFloat(),
            center.y - point.y.toFloat()
        )
    }

}

