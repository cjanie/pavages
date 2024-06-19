package com.cjanie.pavages.logic.complex

import com.cjanie.pavages.logic.isEven
import com.cjanie.pavages.tools.Trigonometry
import com.cjanie.pavages.ui.componants.SetComplexNumbers
import kotlin.Number
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

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
        val iUnit = 1.0
        val i = Complex(0.0, iUnit)

        /**
         * Complex norm
         */
        fun abs(c: Complex): Double = c.abs()

        /**
         * Complex exponential
         */
        fun exponential(c: Complex): Complex {
            val e: Double = exp(c.real)
            return Complex(cos(c.img), e * sin(c.img))
        }

        fun cosine(c: Complex): Complex = (exponential(i * c) + exponential(-i * c)) / 2.0


        fun rotate(angle: Double, c: Complex, r: Double): Complex {


            return Complex(Trigonometry.cosine(angle)*r, Trigonometry.sine(angle)*r)
        }

        fun calc(complexNumbers: List<Complex>, operations : List<String>): Complex {
            if (complexNumbers.size > 1 && operations.size == complexNumbers.size - 1) {
                val first = complexNumbers[complexNumbers.lastIndex - 1]
                val second = complexNumbers.last()

                when (operations.last()) {
                    "+" -> return first + second
                }
            }
            return ONE

        }

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

    // abs() équivalent to
    // Trigonometry.hypotenuseLengthFromPythagoreanTheorem(real, img)
    fun abs(): Double =
        sqrt(this.normSquared())

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

    fun rotate() {
        val z = real + iUnit*img
        val r = sqrt(real.pow(2) + img.pow(2))
        val theta = atan(img/real)
        val trigonometricForm = r*(cos(theta)) + iUnit* sin(theta)
        val c = Complex(r*(cos(theta)), r*(iUnit* sin(theta)))
    }

    // https://www.geeksforgeeks.org/complex-numbers-in-python-set-3-trigonometric-and-hyperbolic-functions/



    // To use destructuring in pair: val (x, y) = Complex(real = 1, img = 2)
    operator fun component1(): Double {
        return real
    }

    operator fun component2(): Double {
        return img
    }

}