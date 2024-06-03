package com.cjanie.pavages.logic

interface Decomposable {
    fun decompose(): Array<Decomposable>

    fun decompose(arrange: Boolean): Array<Decomposable>
}