package com.cjanie.pavages.logic

interface Decomposable {
    fun decompose(): Array<Decomposable>
}