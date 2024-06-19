package com.cjanie.pavages

import com.cjanie.pavages.logic.complex.Complex
import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexTest {

    @Test
    fun complexPair() {
        val (x, y) = Complex(real = 1, img = 2)
        assertEquals(1.0, x, 0.0)
        assertEquals(2.0, y, 0.0)
    }

    @Test
    fun addition() {
        // Prepare
        val complexNumbers = listOf(Complex.ONE, Complex.ONE)
        val operators = listOf("+")
        val calcResult = Complex.calc(complexNumbers, operators)
        assertEquals(2.0, calcResult.real, 0.0)
    }

    @Test
    fun calculations() {
        // Prepare
        val complexNumbers = listOf(Complex.ONE, Complex.ONE, Complex.ONE)
        val operators = listOf("+", "+")
        val calcResult = Complex.calc(complexNumbers, operators)
        assertEquals(3.0, calcResult.real, 0.0)
    }
}