package com.cjanie.pavages

import com.cjanie.pavages.logic.Line
import com.cjanie.pavages.logic.Point
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.pow
import kotlin.math.sqrt

class LineTest {

    @Test
    fun `length of horizontal line`() {
        // A and B have the same y coordinate
        val A = Point("A",-2.0, 0.0)
        val B = Point("B",1.0, 0.0)
        val AB = Line(A, B)
        assertEquals(3.0, AB.length(), 0.0)
    }

    @Test
    fun `length of vertical line` () {
        // A and B have the same x coordinate
        val A = Point("A",-2.0, 1.0)
        val B = Point("B",-2.0, 5.0)
        val AB = Line(A, B)
        assertEquals(4.0, AB.length(), 0.0)
    }

    @Test
    fun `length of obliq line`() {
        val A = Point("A", 0.0, 6.0)
        val B = Point("B", -7.0, 0.0)
        // Trigonometry
        val O = Point("O", 0.0, 0.0)
        val AB = Line(A, B)
        val AO = Line(A, O) // Horizontal line
        val BO = Line(B, O) // Vertical line
        // Check pythagore theorem
        assertEquals(AB.length().pow(2).toFloat(), (AO.length().pow(2) + BO.length().pow(2)).toFloat(), 0.0f)
    }
}