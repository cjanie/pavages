package com.cjanie.pavages

import com.cjanie.pavages.tools.SymmetryTools
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SymmetryTest {

    @Test
    fun `symmetry by horizontal axis`() {
        val axisY = 0.0
        val points = arrayOf(Point(1.0, 1.0), Point(-1.0, -1.0))
        assertEquals(Point(1.0, -1.0),
            SymmetryTools.symmetryByHorizontalAxis(axisY, points as Array<Point?>)[0]
        )
        assertEquals(Point(-1.0, 1.0), SymmetryTools.symmetryByHorizontalAxis(axisY, points)[1])
    }
}