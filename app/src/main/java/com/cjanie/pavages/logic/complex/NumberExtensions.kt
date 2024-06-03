package com.cjanie.pavages.logic.complex

operator fun Number.plus(c: Complex) = Complex(this.toDouble() + c.real, c.img)

operator fun Number.minus(c: Complex) = Complex(this.toDouble() - c.real, -c.img)

operator fun Number.times(c: Complex) = Complex(this.toDouble() * c.real, this.toDouble() * c.img)

operator fun Number.div(c: Complex) = Complex.ONE / c