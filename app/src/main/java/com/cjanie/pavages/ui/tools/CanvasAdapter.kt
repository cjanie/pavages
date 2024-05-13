package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.logic.Graph2D
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.Triangle

class CanvasAdapter(val canvasSize: Float) {
    companion object {
        val goldenTriangleColor = Color(android.graphics.Color.parseColor("#FFD700"))
        val goldenGnomonColor = Color(android.graphics.Color.parseColor("#C0C0C0"))
        val backgroundColor = Color(android.graphics.Color.parseColor("#6e0b14"))
    }

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
    fun center(): Offset {
        return Offset(
            canvasSize / 2f,
            600f//canvasSize / 2f
        )
    }

    // Graph 2D containing shapes
    private val graph2D = Graph2D(heigth = 600.0)

    fun decompose(iteration: Int, arrange: Boolean) : List<Drawing> {
        return graph2D.iterate(iteration, arrange).map {
            Drawing(
                path = path(it as Triangle),
                color = if (it is GoldenTriangle) goldenTriangleColor
                else goldenGnomonColor
            )
        }
    }

    inner class Drawing(val path: Path, val color: Color)

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
            center().x + point.x.toFloat(),
            center().y - point.y.toFloat()
        )
    }



}

