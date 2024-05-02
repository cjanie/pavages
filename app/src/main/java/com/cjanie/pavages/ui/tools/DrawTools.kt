package com.cjanie.pavages.ui.tools
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer

class DrawTools {

    companion object {

        fun createPath(points: Array<Offset>): Path {
            val path = Path()
            path.moveTo(points[0].x, points[0].y)
            for(i in 1 until points.size) {
                path.lineTo(points[i].x, points[i].y)
            }
            path.lineTo(points[0].x, points[0].y)
            path.close()
            return path
        }

        fun getTextResultLayout(text: String, textMeasurer: TextMeasurer) : TextLayoutResult {
            return textMeasurer.measure(
                    text = AnnotatedString(text)
                )
        }
        fun getTextOffset(textLayoutResult: TextLayoutResult, pointOffset: Offset): Offset {
            val textSize = textLayoutResult.size
            return Offset(pointOffset.x - textSize.width/2f, pointOffset.y - textSize.height/2f)
        }
    }
}