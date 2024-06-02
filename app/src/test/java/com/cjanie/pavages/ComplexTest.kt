package com.cjanie.pavages

import com.cjanie.pavages.logic.Complex
import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexTest {

    @Test
    fun complexPair() {
        val (x, y) = Complex(real = 1, img = 2)
        assertEquals(1.0, x, 0.0)
        assertEquals(2.0, y, 0.0)
    }
}