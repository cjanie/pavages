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
        val topBackgroundColor = Color(android.graphics.Color.parseColor("#26619c"))
        val backgroundColor = Color(android.graphics.Color.parseColor("#6e0b14"))
    }

    // Center
    fun center(): Offset {
        return Offset(
            canvasSize / 2f,
            canvasSize * 3f /2f
        )
    }

    fun square(): Drawing {
        val path = DrawTools.createPath(
            arrayOf(
                Offset(0f, 0f),
                Offset(0f, offset(graph2D.symP1).y),
                Offset(canvasSize, offset(graph2D.symP1).y),
                Offset(canvasSize, 0f)
            )
        )
        return Drawing(path, topBackgroundColor)
    }

    // Graph 2D containing shapes
    private val graph2D = Graph2D(heigth = canvasSize.toDouble())

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

