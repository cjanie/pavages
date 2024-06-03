package com.cjanie.pavages.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.logic.Graph2D
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.triangles.Triangle
import com.cjanie.pavages.tools.CheckPointInBounds
import com.cjanie.pavages.ui.tools.DrawTools

class CanvasAdapter(val canvasHeight: Float, val canvasWidth: Float, val triangleHeight: Float) {
    companion object {
        val goldenColor = Color(android.graphics.Color.parseColor("#F5BD02"))
        val silverColor = Color(android.graphics.Color.parseColor("#DBDBDB"))
        val lapisColor = Color(android.graphics.Color.parseColor("#0060FF"))
        val jasperColor = Color(android.graphics.Color.parseColor("#d73b3e"))

        val goldenTriangleColor = goldenColor

        val goldenGnomonColor = silverColor

        val topBackgroundColor = lapisColor

        val backgroundColor = jasperColor
    }

    // Center
    fun center(): Offset {
        return Offset(
            canvasWidth / 2f,//canvasSize / 2f,
            canvasHeight
        )
    }

    fun square(): Drawing {
        val path = DrawTools.createPath(
            arrayOf(
                Offset(0f, 0f),
                Offset(0f, offset(graph2D.symP1).y),
                Offset(canvasWidth, offset(graph2D.symP1).y),
                Offset(canvasWidth, 0f)
            )
        )
        return Drawing(path, topBackgroundColor)
    }

    // Graph 2D containing shapes
    private val graph2D = Graph2D(heigth = triangleHeight.toDouble())

    fun decompose(iteration: Int, arrange: Boolean) : List<Drawing> {
        return graph2D.iterate(iteration, arrange).map {
            Drawing(
                path = path(it as Triangle),
                color = if (it is GoldenTriangle) goldenTriangleColor
                else goldenGnomonColor
            )
        }
    }
/*
    fun arrange(offset: Offset): List<Drawing> {
        return graph2D.arrangeSelectedModel(point(offset)).decomposables().map {
            Drawing(
                path = path(it as Triangle),
                color = if (it is GoldenTriangle) goldenTriangleColor
                else goldenGnomonColor
            )
        }
    }

 */

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



    private fun point(offset: Offset): Point {
        return Point(
            x = (offset.x - center().x) .toDouble(),
            y = (center().y - offset.y).toDouble()
        )
    }
    fun isPointInGoldenTriangle(offset: Offset): Boolean {
        return CheckPointInBounds.isInPointWithinGoldenTriangle(graph2D.goldenTriangle, point(offset))
    }

}

