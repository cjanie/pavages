package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.GoldenTriangle

class Pyramidion(var goldenTriangle: GoldenTriangle, var arrange: Boolean = false): DecomposablesModel {


    val models = mutableListOf<DecomposablesModel>(getModel_2Triangles_1Gnomon())


    fun addModel(model: DecomposablesModel) {
        models.add(model)
    }
    override fun goldenTriangle(): GoldenTriangle {
        return goldenTriangle
    }
    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        this.goldenTriangle = goldenTriangle
        val pyramidionContainer = goldenTriangle.getNextContainer(goldenTriangle)
        models[0].updateGoldenTriangle(pyramidionContainer)
        for (i in 1..models.size - 1) {
            models[i].updateGoldenTriangle(goldenTriangle)
        }
    }

    override fun decomposables(): Array<Decomposable> {
        val decomposables = mutableListOf<Decomposable>()
        for (model in models) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()//arrayOf(*getModel_2Triangles_1Gnomon().decomposables())
    }

    override fun sym(): DecomposablesModel {
        TODO("Not yet implemented")
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    fun getModel_2Triangles_1Gnomon(): GoldenTriangleDecomposables_2Triangles_1Gnomon {
        val model_2Triangles_1Gnomon =
            if (arrange) GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
            else GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
                goldenTriangle = goldenTriangle
            )
        return model_2Triangles_1Gnomon
    }

    fun getSousjacentModel_kite_dart_type(): KyteDartModel {
        return getModel_2Triangles_1Gnomon().getAdjacentKiteDartModel()
    }

    fun getContigueBaseGoldenTriangle(): GoldenTriangleModel {
        return getModel_2Triangles_1Gnomon().getContigueBaseGoldenTriangleModel()
    }

}