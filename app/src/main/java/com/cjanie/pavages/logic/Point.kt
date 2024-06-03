package com.cjanie.pavages.logic

import com.cjanie.pavages.logic.complex.Complex

// As data class, Point can't extend Complex
// There is a conflict for destructuring Complex Class in Pair
data class Point(val name: String = "P", val x: Double, val y: Double) {
    val complex = Complex(real = x, img = y)
}

