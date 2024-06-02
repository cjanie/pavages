package com.cjanie.pavages.logic

import kotlin.Number

// https://medium.com/software-science/showcasing-kotlin-with-complex-numbers-e62417442081
class Complex(val real: Double, val img: Double) {
    constructor(real: Number, img: Number) : this(real.toDouble(), img.toDouble())

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
        TODO("Not impl yet")
        /*
        val den = c.normSquared()
        val num = this * c.conjugate()
        return num / den

         */
    }

    fun conjugate() = Complex(real, -img)
    operator fun component1(): Double {
        return real
    }

    operator fun component2(): Double {
        return img
    }
}