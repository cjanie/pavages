package com.cjanie.pavages.ui.tools
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.cjanie.pavages.Point

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

        fun offsetToPoint(offset: Offset): Point {
            return Point(offset.x.toDouble(), offset.y.toDouble())
        }

        fun offsetsToPoints(offsets: Array<Offset>): Array<Point?> {
            val points = arrayOfNulls<Point>(offsets.size)
            if(offsets.isNotEmpty()) {
                for(i in offsets.indices) {
                    points[i] = offsetToPoint(offsets[i])
                }
            }
            return points
        }
    }
}