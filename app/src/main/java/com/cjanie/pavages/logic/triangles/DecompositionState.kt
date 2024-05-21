package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposablesModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2Triangles_1Gnomon
import com.cjanie.pavages.tools.CheckPointInBounds

class DecompositionState {
    companion object {
        private val models = mutableListOf<DecomposablesModel>()
    }

    fun getModels(): List<DecomposablesModel> {
        return models
    }

    fun updateModels(newModels: List<DecomposablesModel>) {
        models.clear()
        models.addAll(newModels)
    }

    fun arrangeSelectedModel(point: Point): GoldenTriangleDecomposables_2Triangles_1Gnomon? {
        for (i in models.indices) {
            if(models[i] is GoldenTriangleDecomposables_2Triangles_1Gnomon
                && CheckPointInBounds.isInPointWithinGoldenTriangle(models[i].goldenTriangle(), point)) {
                val arranged = (models[i] as GoldenTriangleDecomposables_2Triangles_1Gnomon).arrange()
                models.removeAt(i)
                models.add(i, arranged)
                return models[i] as GoldenTriangleDecomposables_2Triangles_1Gnomon
            }
        }
        return null
    }

    fun decomposables(): Array<Decomposable> {
        val decomposables = mutableListOf<Decomposable>()
        for (model in models) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()
    }

}
