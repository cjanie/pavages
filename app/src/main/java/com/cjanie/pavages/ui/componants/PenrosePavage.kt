package com.cjanie.pavages.ui.componants

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.tools.TrigonometryTools

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
        val angleCDE = angleDAB / 2.0
        val pointA = Offset(0F, startY + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB).toFloat())
        val pointB = Offset(losangeSideLength.toFloat(), startY + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, angleDAB).toFloat())
        val pointC = Offset(losangeSideLength.toFloat() + TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB).toFloat(), startY + 0F)
        val pointD = Offset(TrigonometryTools.adjacentSideLength(losangeSideLength, angleDAB).toFloat(), startY + 0F)
        val pointE = Offset(pointB.x - (losangeSideLength/2.0).toFloat(), pointB.y - TrigonometryTools.oppositeSideLengthFromAdjacentSideAndAngle(losangeSideLength/2.0, angleCDE).toFloat())

        val kite = Path()
        kite.moveTo(pointB.x, pointB.y)
        kite.lineTo(pointB.x, pointB.y)
        kite.lineTo(pointC.x, pointC.y)
        kite.lineTo(pointD.x, pointD.y)
        kite.lineTo(pointE.x, pointE.y)
        kite.lineTo(pointB.x, pointB.y)
        kite.close()
        drawPath(kite, Color.Yellow)

        val dart = Path()
        dart.moveTo(pointA.x, pointA.y)
        dart.lineTo(pointB.x, pointB.y)
        dart.lineTo(pointE.x, pointE.y)
        dart.lineTo(pointD.x, pointD.y)
        dart.lineTo(pointA.x, pointA.y)
        dart.close()
        drawPath(dart, Color.Green)

        // Symmetry axe CD
        val pointASym = Offset(pointA.x, pointD.y - pointA.y + startY)
        val pointBSym = Offset(pointB.x, startY - pointB.y + startY)
        val pointESym = Offset(pointE.x, pointD.y - pointE.y + startY)

        val dartSym = Path()
        dartSym.moveTo(pointD.x, pointD.y)
        dartSym.lineTo(pointASym.x, pointASym.y)
        dartSym.lineTo(pointBSym.x, pointBSym.y)
        dartSym.lineTo(pointESym.x, pointESym.y)
        dartSym.lineTo(pointD.x, pointD.y)
        dartSym.close()
        drawPath(dartSym, Color.Green)

        val kiteSym = Path()
        kiteSym.moveTo(pointD.x, pointD.y)
        kiteSym.lineTo(pointESym.x, pointESym.y)
        kiteSym.lineTo(pointBSym.x, pointBSym.y)
        kiteSym.lineTo(pointC.x, pointC.y)
        kiteSym.lineTo(pointD.x, pointD.y)
        kiteSym.close()
        drawPath(kiteSym, Color.Yellow)

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

    }
}