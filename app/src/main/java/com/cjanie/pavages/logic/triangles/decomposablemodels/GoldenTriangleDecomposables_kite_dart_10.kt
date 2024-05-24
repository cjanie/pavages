package com.cjanie.pavages.logic.triangles.decomposablemodels

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.enums.Position
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.tools.Symmetry

class GoldenTriangleDecomposables_kite_dart_10(
    goldenTriangle: GoldenTriangle, var arrange: Boolean
) : GoldenTriangleDecomposables_Kite_Dart(goldenTriangle), DecomposablesModel {

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

    override fun sym(): GoldenTriangleDecomposables_Kite_Dart {
        return GoldenTriangleDecomposables_Dart_atBottom_sym(container)
    }

    override fun arrangeModelContainingPoint(point: Point) {
        TODO("Not yet implemented")
    }

    override fun decomposables(): Array<Decomposable> {
        return arrayOf(*kite(), *dart())
    }

    private fun symNewContainerUnderBaseAxis() = getSymNewContainerUnderBaseAxis(
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

    private fun symTopGoldenTriangle() = getNextContainer(symNewContainerUnderBaseAxis())

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

    fun getSymNewContainerUnderBaseAxis(parentContainer: GoldenTriangle): GoldenTriangle {
        val AA = Symmetry.symmetryByHorizontalAxis(parentContainer.points[1].y, parentContainer.points[0])
        return GoldenTriangle(AA, parentContainer.points[2], parentContainer.points[1])
    }

    fun getNextContainer(currentContainer: GoldenTriangle): GoldenTriangle {
        return GoldenTriangle(
            currentContainer.points[0],
            currentContainer.symP1,
            currentContainer.P1
        )
    }

}