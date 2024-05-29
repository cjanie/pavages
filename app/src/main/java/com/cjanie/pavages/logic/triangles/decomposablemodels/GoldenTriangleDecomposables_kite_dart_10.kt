package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.enums.Position
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry

class GoldenTriangleDecomposables_kite_dart_10(
    goldenTriangle: GoldenTriangle,
    var models: Array<DecomposablesModel> = emptyArray(),
    var arrange: Boolean
) : GoldenTriangleDecomposables_Kite_Dart(goldenTriangle) {

    var container = goldenTriangle
    override fun goldenTriangle(): GoldenTriangle {
        return container
    }

    override fun updateGoldenTriangle(goldenTriangle: GoldenTriangle) {
        container = goldenTriangle
    }

    override fun kite(): Array<Decomposable> {
        return arrayOf(
            *symKiteDartModel().decomposables(),
            symTopGoldenTriangleModel().goldenTriangles()[0],
            *symBottomGoldenTriangleModel().decomposables()
        )
    }

    override fun dart(): Array<Decomposable> {
        return arrayOf(
            *baseGoldenTriangleModel().decomposables(),
            symTopGoldenTriangleModel().goldenGnomon()
        )
    }

    override fun createModel(goldenTriangle: GoldenTriangle, arrange: Boolean): GoldenTriangleDecomposables_Kite_Dart {
        TODO("Not yet implemented")
    }

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        return GoldenTriangleDecomposables_Dart_atBottom_sym(container)
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun decomposables(): Array<Decomposable> {
        return (models[0] as GoldenTriangleDecomposables_Kite_Dart).createModel(symNewContainerUnderBaseAxis(), arrange).decomposables()
        //return arrayOf(*kite(), *dart())
    }

    private fun symNewContainerUnderBaseAxis() = getSymUnderBaseAxis(
        container.getNextContainer(container)
    )
    private fun symKiteDartModel() =
        if (arrange)
            GoldenTriangleDecomposables_Dart_atBottom_sym(
                symNewContainerUnderBaseAxis()
            )
        else GoldenTriangleDecomposables_Kite_atBottom_sym(
            symNewContainerUnderBaseAxis()
        )

    private fun symTopGoldenTriangle() = getScaledGoldenTriangle(symNewContainerUnderBaseAxis())

    private fun symTopGoldenTriangleModel() =
        GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(symTopGoldenTriangle()).sym()

    private fun symBottomGoldenTriangle() =
        GoldenTriangle(
            Symmetry.symmetryByHorizontalAxis(container.symP1.y, container.symP2),
            symNewContainerUnderBaseAxis().symP3,
            symNewContainerUnderBaseAxis().points[2]
        )

    private fun symBottomGoldenTriangleModel() =
        DecompositionModel(symBottomGoldenTriangle(), arrayOf(symBottomGoldenTriangle()))

    private fun baseGoldenTriangleModel() =
        if (arrange)
            GoldenTriangleDecomposables_bottom_golden_triangle(
                container,
                arrayOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(container).sym()),
                Position.START
            )
        else
            GoldenTriangleDecomposables_bottom_golden_triangle(
                goldenTriangle = container,
                arrayOf(GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(goldenTriangle = container).sym()),
                Position.START
            )

    fun getSymUnderBaseAxis(goldenTriangle: GoldenTriangle): GoldenTriangle {
        val AA = Symmetry.symmetryByHorizontalAxis(goldenTriangle.points[1].y, goldenTriangle.points[0])
        return GoldenTriangle(AA, goldenTriangle.points[2], goldenTriangle.points[1])
    }

    fun getScaledGoldenTriangle(initial: GoldenTriangle): GoldenTriangle {
        return GoldenTriangle(
            initial.points[0],
            initial.symP1,
            initial.P1
        )
    }

}