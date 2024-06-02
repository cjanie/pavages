package com.cjanie.pavages.logic

import kotlin.Number

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

operator fun Number.plus(c: Complex) = Complex(this.toDouble() + c.real, c.img)

operator fun Number.minus(c: Complex) = Complex(this.toDouble() - c.real, -c.img)

operator fun Number.times(c:Complex) = Complex(this.toDouble() * c.real, this.toDouble() * c.img)

operator fun Number.div(c: Complex) = ONE / c