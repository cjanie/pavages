package com.cjanie.pavages.ui.componants

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
        val angleCDE = 36.0
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

        // Right
        val D = Offset(pointC.x + TrigonometryTools.adjacentSideLength(losangeSideLength, 36.0).toFloat(), pointC.y - TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, 36.0).toFloat())
        val B = Offset(pointC.x + TrigonometryTools.adjacentSideLength(losangeSideLength, 36.0).toFloat(), pointC.y + TrigonometryTools.oppositeSideLengthFromHypotenuseAndAngle(losangeSideLength, 36.0).toFloat())
        val A = Offset(D.x + D.x - pointC.x, pointC.y)
        val E = Offset(A.x - TrigonometryTools.hypotenuseLength(pointE.x.toDouble() - pointA.x.toDouble(), 36.0).toFloat(), pointC.y)

        // Top

        val ATop = Offset(pointBSym.x + D.x - pointC.x, D.y - pointC.y + pointBSym.y)
        val ETop = Offset(pointBSym.x + TrigonometryTools.hypotenuseLength(pointB.x.toDouble() - pointE.x.toDouble(), 36.0).toFloat(), pointBSym.y)

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

        val dartRight = Path()
        dartRight.moveTo(E.x, E.y)
        dartRight.lineTo(D.x, D.y)
        dartRight.lineTo(A.x, A.y)
        dartRight.lineTo(B.x, B.y)
        dartRight.lineTo(E.x, E.y)
        dartRight.close()
        drawPath(dartRight, Color.Green)

        val kiteRight = Path()
        kiteRight.moveTo(pointC.x, pointC.y)
        kiteRight.lineTo(D.x, D.y)
        kiteRight.lineTo(E.x, E.y)
        kiteRight.lineTo(B.x, B.y)
        kiteRight.lineTo(pointC.x, pointC.y)
        kiteRight.close()
        drawPath(kiteRight, Color.Yellow)

        val dartTop = Path()
        dartTop.moveTo(pointBSym.x, pointBSym.y)
        dartTop.lineTo(ATop.x, ATop.y)
        dartTop.lineTo(D.x, D.y)
        dartTop.lineTo(ETop.x, ETop.y)
        dartTop.close()
        drawPath(dartTop, Color.Green)

        val kiteTop = Path()
        kiteTop.moveTo(pointBSym.x, pointBSym.y)
        kiteTop.lineTo(pointC.x, pointC.y)
        kiteTop.lineTo(D.x, D.y)
        kiteTop.lineTo(ETop.x, ETop.y)
        kiteTop.lineTo(pointBSym.x, pointBSym.y)
        kiteTop.close()
        drawPath(kiteTop, Color.Yellow)


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
    }
}