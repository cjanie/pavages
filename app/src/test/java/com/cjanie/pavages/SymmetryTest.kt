package com.cjanie.pavages

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.tools.Symmetry
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SymmetryTest {

    @Test
    fun `symmetry by horizontal axis`() {
        val axisY = 0.0
        val points = arrayOf(Point("A", 1.0, 1.0), Point("B", -1.0, -1.0))
        assertEquals(
            Point("A'",1.0, -1.0),
            Symmetry.symmetryByHorizontalAxis(axisY, points)[0]
        )
        assertEquals(Point("B'", -1.0, 1.0), Symmetry.symmetryByHorizontalAxis(axisY, points)[1])
    }

    @Test
    fun `symetry by horizontal axis over 0`() {
        val axisY = 1.0
        val point = Point("A", 2.0, 2.0)
        val sym = Symmetry.symmetryByHorizontalAxis(axisY, point)
        assertEquals(Point("A'", 2.0, 0.0), sym)
    }

    @Test
    fun `symetry by horizontal axis under 0`() {
        val axisY = -1.0
        val point = Point("A",  1.0, 1.0)
        val sym = Symmetry.symmetryByHorizontalAxis(axisY, point)
        assertEquals(Point("A'", 1.0, -3.0), sym)
    }

    @Test
    fun `symetry by vertical axis`() {
        val axisX = 0.0
        val point = Point("A", 1.0, 1.0)
        val sym = Symmetry.symmetryByVerticalAxis(axisX, point)
        assertEquals(Point("A'", -1.0, 1.0), sym)
    }

    @Test
    fun `symetry by vertical axis before 0`() {
        val axisX = -1.0
        val point = Point("A", 1.0, 1.0)
        val sym = Symmetry.symmetryByVerticalAxis(axisX, point)
        assertEquals(Point("A'", -3.0, 1.0), sym)
    }

    @Test
    fun `symetry by vertical axis after 0`() {
        val axisX = 1.0
        val point = Point("A", 2.0, 1.0)
        val sym = Symmetry.symmetryByVerticalAxis(axisX, point)
        assertEquals(Point("A'", 0.0, 1.0), sym)
    }

}