package com.cjanie.pavages.ui.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.logic.Graph2D
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.Triangle

class CanvasGraph2DAdapter(canvasSize: Float) {

    companion object {
        val goldenTriangleColor = Color(android.graphics.Color.parseColor("#FFD700"))
        val goldenGnomonColor = Color(android.graphics.Color.parseColor("#C0C0C0"))
    }

    val decomposeStep1 by lazy { DecomposeStep1() }

    val decomposeStep2 by lazy { DecomposeStep2() }

    val decomposeStep2ArrangeAdjacentGoldenTriangles by lazy { DecomposeStep2ArrangeAdjacentGoldenTriangles() }

    val decomposeStep3 by lazy { DecomposeStep3() }

    val decomposeStep4 by lazy { DecomposeStep4() }

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
    val goldenTriangle = Drawing(path(graph2D.goldenTriangle), goldenTriangleColor)

    // Decompose
    inner class DecomposeStep1 {
        // Step 1 : 1 golden triangle, 1 golden gnomon
        private val goldenTriangle = Drawing(path(graph2D.decomposeStep1.goldenTriangle), goldenTriangleColor)
        private val goldenGnomon = Drawing(path(graph2D.decomposeStep1.goldenGnomon), goldenGnomonColor)
        val drawings = arrayOf(goldenTriangle, goldenGnomon)
        val text = "Decompose the Golden Triangle"

    }
    inner class DecomposeStep2 {
        // Step 2 : 2 golden triangle, 1 golden gnomon
        private val goldenGnomon = Drawing(path(graph2D.decomposeStep2.goldenGnomon), goldenGnomonColor)

        private val goldenTriangles = Array(2) {
            Drawing(path(graph2D.decomposeStep2.goldenTriangles[it]), goldenTriangleColor)
        }

        val drawings: Array<Drawing> = arrayOf(goldenGnomon, goldenTriangles[0], goldenTriangles[1])
        val text = "Decompose the Golden Gnomon"
    }

    inner class DecomposeStep2ArrangeAdjacentGoldenTriangles {
        private val goldenTriangles = Array(2) {
            Drawing(path(graph2D.decomposeStep2ArrangeAdjacentGoldenTriangles.goldenTriangles[it]), goldenTriangleColor)
        }
        private val goldenGnomon = Drawing(path(graph2D.decomposeStep2ArrangeAdjacentGoldenTriangles.goldenGnomon), goldenGnomonColor)
        val drawings = arrayOf(goldenGnomon, goldenTriangles[0], goldenTriangles[1])
        val text = "Arrange"
    }

    inner class DecomposeStep3 {
        private val goldenTriangle = Drawing(path(graph2D.decomposeStep3.goldenTriangle), goldenTriangleColor)
        private val goldenGnomon = Drawing(path(graph2D.decomposeStep3.goldenGnomon), goldenGnomonColor)
        val baseGoldenTriangle = Drawing(path(graph2D.decomposeStep3.baseGoldenTriangle), goldenTriangleColor)
        val baseGoldenGnomon = Drawing(path(graph2D.decomposeStep3.baseGoldeGnomon), goldenGnomonColor)
        val otherGoldenTriangle = Drawing(path(graph2D.decomposeStep3.otherGoldenTriangle), goldenTriangleColor)
        val otherGoldenGnomon = Drawing(path(graph2D.decomposeStep3.otherGoldenGnomon), goldenGnomonColor)
        private val goldenGnomon1 = Drawing(path(graph2D.decomposeStep3.goldenGnomon1), goldenGnomonColor)
        private val goldenGnomon2 = Drawing(path(graph2D.decomposeStep3.goldenGnomon2), goldenGnomonColor)
        private val goldenTriangle1 = Drawing(path(graph2D.decomposeStep3.goldenTriangle1), goldenTriangleColor)
        private val goldenTriangle2 = Drawing(path(graph2D.decomposeStep3.goldenTriangle2), goldenTriangleColor)
        val drawings = arrayOf(
            goldenTriangle, goldenGnomon, baseGoldenTriangle, otherGoldenTriangle,
            baseGoldenGnomon, otherGoldenGnomon, goldenGnomon1, goldenGnomon2,
            goldenTriangle1, goldenTriangle2)

    }

    inner class DecomposeStep4 {
        private val goldenGnomon1 = Drawing(path(graph2D.decomposeStep4.goldenGnomon), Color.Red)
        private val goldenGnomon = Drawing(path(graph2D.decomposeStep4.goldenGnomon2), Color.Red)
        val drawings = arrayOf(goldenGnomon1, goldenGnomon)

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
            center.x + point.x.toFloat(),
            center.y - point.y.toFloat()
        )
    }



}

