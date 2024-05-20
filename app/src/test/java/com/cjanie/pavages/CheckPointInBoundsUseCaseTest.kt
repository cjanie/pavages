package com.cjanie.pavages

import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.CheckPointInBounds
import com.cjanie.pavages.tools.Trigonometry
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test



class CheckPointInBoundsUseCaseTest {
    @Test
    fun `point is within the bounds right side of the golden triangle`() {
        val goldenTriangle = GoldenTriangle()
        val pointIn = Point(x = 0.1, y = 1.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointIn)
        assertTrue(isPointInBounds)
    }

    @Test
    fun `point is within the bounds left side of the golden triangle`() {
        val goldenTriangle = GoldenTriangle()
        val pointIn = Point(x = -0.1, y = 1.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointIn)
        assertTrue(isPointInBounds)
    }

    @Test
    fun `not in bounds to the right`() {
        val goldenTriangle = GoldenTriangle()
        val pointOut = Point(x = 1.0, y = 1.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointOut)
        assertFalse(isPointInBounds)
    }

    @Test
    fun `not in bounds to the left`() {
        val goldenTriangle = GoldenTriangle()
        val pointOut = Point(x = -1.0, y = 1.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointOut)
        assertFalse(isPointInBounds)
    }

    @Test
    fun `point is over`() {
        val goldenTriangle = GoldenTriangle()
        val pointOver = Point(x = 0.1, y = 2.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointOver)
        assertFalse(isPointInBounds)
    }

    @Test
    fun `point is under`() {
        val goldenTriangle = GoldenTriangle()
        val pointUnder = Point(x = 0.1, y = -2.0)
        val isPointInBounds: Boolean = CheckPointInBounds.isInPointWithinGoldenTriangle(goldenTriangle, pointUnder)
        assertFalse(isPointInBounds)
    }

}