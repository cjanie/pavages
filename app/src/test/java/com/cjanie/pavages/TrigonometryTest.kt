package com.cjanie.pavages

import com.cjanie.pavages.tools.Trigonometry.Companion.adjacentSideLength
import com.cjanie.pavages.tools.Trigonometry.Companion.hypotenuseLength
import com.cjanie.pavages.tools.Trigonometry.Companion.hypotenuseLengthFromPythagoreanTheorem
import com.cjanie.pavages.tools.Trigonometry.Companion.oppositeSideLengthFromAdjacentSideAndAngle
import com.cjanie.pavages.tools.Trigonometry.Companion.oppositeSideLengthFromHypotenuseAndAngle
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class TrigonometryTest {

    @Test
    fun `adjacent side length calculation is correct`() {
        val hypotenuseLength = 9.0 // cm
        val angle = 40.0 // degrees
        val adjacentSideLength = adjacentSideLength(hypotenuseLength, angle)
        assertEquals(6.894399988070802, adjacentSideLength, 0.0)

    }

    @Test
    fun `opposite side length calculation is correct`() {
        val hypotenuseLength = 23.0 // cm
        val angle = 80.0 // degrees
        val oppositeSideLength = oppositeSideLengthFromHypotenuseAndAngle(hypotenuseLength, angle)
        assertEquals(22.650578319280786, oppositeSideLength, 0.0)
    }

    @Test
    fun `opposite side length calculation from adjacente side is correct` () {
        val adjacentSideLength = 6.0
        val angle = 37.0
        val oppositeSideLength = oppositeSideLengthFromAdjacentSideAndAngle(adjacentSideLength, angle)
        assertEquals(4.521324300616765, oppositeSideLength, 0.0)
    }

    @Test
    fun `hypotenuse side length calculation is correct`() {
        val adjacentSideLength = 6.0
        val angle = 37.0
        val hypotenuse = hypotenuseLength(adjacentSideLength, angle)
        assertEquals(7.512813948937354, hypotenuse, 0.0)
    }

    @Test
    fun `hypotenuse side length from Pythagore theorem`() {
        assertEquals(2.8284271247461903, hypotenuseLengthFromPythagoreanTheorem(2.0, 2.0), 0.0)
    }

}