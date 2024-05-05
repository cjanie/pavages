package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
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

    // Set the graph2D size fitting the view size
    private val graph2D = Graph2D(canvasSize.toDouble())
    val P = Offset(
        center.x + graph2D.P.x.toFloat(),
        center.y - graph2D.P.y.toFloat())

    val triangle = graph2D.triangle.points
    val A = offset(graph2D.triangle.points[0])
    val B = offset(graph2D.triangle.points[1])
    val C = offset(graph2D.triangle.points[2])

    val trianglePath = DrawTools.createPath(arrayOf(A, B, C))

    private fun offset(point: Point): Offset {
        return Offset(
            center.x + point.x.toFloat(),
            center.y - point.y.toFloat()
        )
    }

}

