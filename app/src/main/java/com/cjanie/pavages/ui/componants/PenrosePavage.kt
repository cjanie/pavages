package com.cjanie.pavages.ui.componants

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.cjanie.pavages.tools.SymmetryTools
import com.cjanie.pavages.tools.TrigonometryTools
import com.cjanie.pavages.ui.tools.DrawTools

// Losange ABCD
// AB = 5cm
// A = 72°
@Composable
fun PenrosePavage(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 2F
        val startY = 800F

        val losangeSideLength = 500.0
        val angleDAB = 72.0
        val angleCDE = 36.0
        val pointA = Offset(0F, startY + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB).toFloat())
        val pointB = Offset(losangeSideLength.toFloat(), startY + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB).toFloat())
        val pointC = Offset(losangeSideLength.toFloat() + TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB).toFloat(), startY + 0F)
        val pointD = Offset(TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB).toFloat(), startY + 0F)
        val pointE = Offset(pointB.x - (losangeSideLength/2.0).toFloat(), pointB.y - TrigonometryTools.oppositeSideLengthFromAdjacentSideAndAngle(losangeSideLength/2.0, angleCDE).toFloat())

        // Symmetry axe CD
        val pointASym = Offset(pointA.x, pointD.y - pointA.y + startY)
        val pointBSym = Offset(pointB.x, startY - pointB.y + startY)
        val pointESym = Offset(pointE.x, pointD.y - pointE.y + startY)

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
        val dartPoints = arrayOf(pointA, pointB, pointE, pointD)
        val dartSymPoints = arrayOf(pointD, pointASym, pointBSym, pointESym)
        val dartRightPoints = arrayOf(E, D, A, B)
        val dartTopPoints = arrayOf(pointBSym, ATop, D, ETop)

        // Kites
        val kitePoints = arrayOf(pointB, pointC, pointD, pointE)
        val kiteSymPoints = arrayOf(pointD, pointESym, pointBSym, pointC)
        val kiteRightPoints = arrayOf(pointC, D, E, B)
        val kiteTopPoints = arrayOf(pointBSym, pointC, D, ETop)
        val kiteBottomPoints = arrayOf(pointC, pointB, EBottom, B)

        val darts = listOf(dartPoints, dartSymPoints, dartRightPoints, dartTopPoints)
        for(dart in darts) {
            val path = DrawTools.createPath(dart)
            drawPath(path, Color.Green)
        }

        val kites = listOf(kitePoints, kiteSymPoints, kiteRightPoints, kiteTopPoints, kiteBottomPoints)
        for(kite in kites) {
            val path = DrawTools.createPath(kite)
            drawPath(path, Color.Yellow)
        }


        drawLine(Color.Black, pointA, pointB, strokeWidth)
        drawLine(Color.Black, pointB, pointC, strokeWidth)
        drawLine(Color.Black, pointC, pointD, strokeWidth)
        drawLine(Color.Black, pointD, pointA, strokeWidth)
        drawLine(Color.Black, pointD, pointE, strokeWidth)
        drawLine(Color.Black, pointE, pointB, strokeWidth)

        // Sym

        drawLine(Color.Black, pointASym, pointBSym, strokeWidth)
        drawLine(Color.Black, pointBSym, pointC, strokeWidth)
        drawLine(Color.Black, pointD, pointASym, strokeWidth)
        drawLine(Color.Black, pointD, pointESym, strokeWidth)
        drawLine(Color.Black, pointESym, pointBSym, strokeWidth)

        // Right
        drawLine(Color.Black, pointC, D, strokeWidth)
        drawLine(Color.Black, pointC, B, strokeWidth)
        drawLine(Color.Black, D, A, strokeWidth)
        drawLine(Color.Black, A, B, strokeWidth)
        drawLine(Color.Black, D, E, strokeWidth)
        drawLine(Color.Black, E, B, strokeWidth)

        // Top
        drawLine(Color.Black, pointBSym, ATop)
        drawLine(Color.Black, D, ATop)
        drawLine(Color.Black, pointBSym, ETop)
        drawLine(Color.Black, D, ETop)

        // Bottom
        drawLine(Color.Black, pointB, EBottom)
        drawLine(Color.Black, B, EBottom)
    }
}