package com.cjanie.pavages.logic.complex

import com.cjanie.pavages.logic.isEven
import kotlin.Number

// https://medium.com/software-science/showcasing-kotlin-with-complex-numbers-e62417442081
open class Complex(val real: Double, val img: Double) {
    constructor(real: Number, img: Number) : this(real.toDouble(), img.toDouble())

    companion object {
        /**
         * Complex 0 = 0 + 0i
         */
        val ZERO = Complex(0.0, 0.0)

        /**
         * Complex 1 = 1 + 0i
         */
        val ONE = Complex(1.0, 0.0)

        /**
         * Complex unit = 0 + i
         */
        val i = Complex(0.0, 1.0)

    }
    operator fun unaryMinus() = Complex(-real, -img)

    operator fun plus(c: Complex) = Complex(real + c.real, img + c.img)

    operator fun plus(n: Number) = Complex(real + n.toDouble(), img)

    operator fun minus (c: Complex) = Complex(real - c.real, img - c.img)

    operator fun minus (n: Number) = Complex(real - n.toDouble(), img)

    operator fun times(c: Complex) = Complex(
        real * c.real - img * c.img,
        real * c.img + img * c.real
    )

    operator fun times(n: Number) = Complex(
        real * n.toDouble(),
        img * n.toDouble()
    )

    operator fun div(n: Number) = Complex(
        real / n.toDouble(),
        img / n.toDouble()
    )

    operator fun div(c: Complex): Complex {
        val den = c.normSquared()
        val num = this * c.conjugate()
        return num / den
    }

    fun normSquared() = real * real + img * img

    fun conjugate() = Complex(real, -img)

    infix fun to(exponent: Int): Complex {
        if (exponent == 0)
            return ONE
        if (exponent == 1)
            return this

        val half = to(exponent / 2)
        return if (isEven(exponent)) {
            half * half
        } else {
            half * half * this
        }
    }

    // To use destructuring in pair: val (x, y) = Complex(real = 1, img = 2)
    operator fun component1(): Double {
        return real
    }

    operator fun component2(): Double {
        return img
    }

}