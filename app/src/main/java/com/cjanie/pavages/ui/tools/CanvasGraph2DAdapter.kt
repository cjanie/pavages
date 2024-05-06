package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.logic.Graph2D
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.Triangle

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

    // Initial shape path
    val goldenTrianglePath = path(graph2D.goldenTriangle)

    // Decompose
    // Step 1 : 1 golden triangle, 1 golden gnomon
    val decomposeGoldenTrianglePath = path(graph2D.decomposeGoldenTriangle)

    val decomposeGoldenGnomonPath = path(graph2D.decomposeGoldenGnomon)

    private fun path(triangle: Triangle): Path {
        return DrawTools.createPath(
            arrayOf(
                offset(triangle.points[0]),
                offset(triangle.points[1]),
                offset(triangle.points[2])
            )
        )
    }

    private fun offset(point: Point): Offset {
        return Offset(
            center.x + point.x.toFloat(),
            center.y - point.y.toFloat()
        )
    }



}

