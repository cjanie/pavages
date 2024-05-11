package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.triangles.GoldenTriangle

interface DecomposablesModel {

    fun updateGoldenTriangle(goldenTriangle: GoldenTriangle)
    fun decomposables(): Array<Decomposable>
}