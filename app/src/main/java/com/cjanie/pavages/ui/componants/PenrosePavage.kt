package com.cjanie.pavages.ui.componants

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.cjanie.pavages.Point
import com.cjanie.pavages.tools.SymmetryTools
import com.cjanie.pavages.tools.TrigonometryTools
import com.cjanie.pavages.ui.tools.DrawTools
import com.cjanie.pavages.ui.tools.Graph2D

// Losange ABCD
// AB = 5cm
// A = 72°
@Composable
fun PenrosePavage(modifier: Modifier) {
    Canvas(modifier = modifier) {
        
        val strokeWidth = 1F
        val horizontalAxisY = 800.0
        val verticalAxixX = 0.0
        val graph2D = Graph2D(verticalAxixX, horizontalAxisY)

        // Losange ABCD
        // sides (AB, BC, CD, DA) length = 5cm
        // angle DAB = 72°
        // angle ABC = 72° + 36°
        // angle ABE = 36°
        val losangeSideLength = 500.0
        val angleDAB = 72.0
        val angleCDE = 36.0

        val a = Point(
            0.0,
            TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB)
        )
        val pointA = Offset(verticalAxixX.toFloat() + a.x.toFloat(), horizontalAxisY.toFloat() + a.y.toFloat())

        val b = Point(
            losangeSideLength,
            TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB)
        )
        val pointB = Offset(verticalAxixX.toFloat() + b.x.toFloat(), horizontalAxisY.toFloat() + b.y.toFloat())

        val c = Point(
            losangeSideLength + TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB),
            0.0
        )
        val pointC = Offset(verticalAxixX.toFloat() + c.x.toFloat(), horizontalAxisY.toFloat() + c.y.toFloat())

        val d = Point(
            TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB),
            0.0
        )

        val e = Point(
            b.x - losangeSideLength/2.0,
            b.y - TrigonometryTools.oppositeSideLengthFromAdjacentSideAndAngle(losangeSideLength/2.0, angleCDE)
        )

        val pointE = Offset(verticalAxixX.toFloat() + e.x.toFloat(), horizontalAxisY.toFloat() + e.y.toFloat())

        // Symmetry axe CD
        val pointBSym = Offset(pointB.x, horizontalAxisY.toFloat() - pointB.y + horizontalAxisY.toFloat())

        // Right
        val D = Offset(pointC.x + TrigonometryTools.adjacentSideLength(losangeSideLength, 36.0).toFloat(), pointC.y - TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, 36.0).toFloat())
        val B = Offset(pointC.x + TrigonometryTools.adjacentSideLength(losangeSideLength, 36.0).toFloat(), pointC.y + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, 36.0).toFloat())
        val A = Offset(D.x + D.x - pointC.x, pointC.y)
        val E = Offset(A.x - TrigonometryTools.hypotenuseLength(pointE.x.toDouble() - pointA.x.toDouble(), 36.0).toFloat(), pointC.y)

        // Top

        val ATop = Offset(pointBSym.x + D.x - pointC.x, D.y - pointC.y + pointBSym.y)
        val ETop = Offset(pointBSym.x + TrigonometryTools.hypotenuseLength(pointB.x.toDouble() - pointE.x.toDouble(), 36.0).toFloat(), pointBSym.y)

        // Bottom
        val EBottom = Offset(ETop.x, pointC.y + pointC.y - ETop.y)

        // Darts
        val dartPoints = arrayOf(a, b, e, d)
        val dartSymPoints = SymmetryTools.symmetryByHorizontalAxis(d.y, dartPoints)
        val dartRightPoints = arrayOf(E, D, A, B)
        val dartTopPoints = arrayOf(pointBSym, ATop, D, ETop)

        // Kites
        val kitePoints = arrayOf(b, c, d, e)
        val kiteSymPoints = SymmetryTools.symmetryByHorizontalAxis(d.y, kitePoints)
        val kiteRightPoints = arrayOf(pointC, D, E, B)
        val kiteTopPoints = arrayOf(pointBSym, pointC, D, ETop)
        val kiteBottomPoints = arrayOf(pointC, pointB, EBottom, B)

        val darts = listOf(
            graph2D.pointsToOffsets(dartPoints),
            graph2D.pointsToOffsets(dartSymPoints),
            dartRightPoints, dartTopPoints)
        for(dart in darts) {
            val path = DrawTools.createPath(dart)
            drawPath(path, Color.Green)
            drawPath(path, Color.Black, 1F, Stroke(width = strokeWidth))
        }

        val kites = listOf(
            graph2D.pointsToOffsets(kitePoints),
            graph2D.pointsToOffsets(kiteSymPoints),
            kiteRightPoints, kiteTopPoints, kiteBottomPoints)
        for(kite in kites) {
            val path = DrawTools.createPath(kite)
            drawPath(path, Color.Yellow)
            drawPath(path, Color.Black, 1F, Stroke(width = strokeWidth))
        }

    }
}