package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.enums.Position
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecomposablesModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Dart_atBottom_sym
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Kite_atBottom
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_Kite_atBottom_sym
import com.cjanie.pavages.logic.triangles.decomposablemodels.DecompositionModel
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_bottom_golden_triangle
import com.cjanie.pavages.logic.triangles.decomposablemodels.GoldenTriangleDecomposables_kite_dart_10
import com.cjanie.pavages.logic.triangles.decomposablemodels.Pyramidion
import com.cjanie.pavages.tools.Symmetry
import com.cjanie.pavages.tools.Trigonometry
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

class GoldenTriangle(
    oppositeToBase: Point = Point(name = "A", x = 0.0, y = height),
    basePoint1: Point = Point(name = "B", x = -baseRatio / 2.0, y = 0.0),
    basePoint2: Point = Point(name = "C", x = baseRatio / 2.0, y = 0.0)
): IsoscelesTriangle(oppositeToBase, basePoint1, basePoint2),
    Decomposable {

    init {

        // check that the duplicated side is in the golden ration phi to the base side
        val decimal = BigDecimal(duplicatedSideLength() / baseSideLength()).setScale(1, RoundingMode.DOWN)
        println("${duplicatedSideLength() / baseSideLength()}!!!!!!!!!!!!!!!!!!")
        val phi = BigDecimal(Number.GOLDEN_NUMBER_PHI).setScale(1, RoundingMode.DOWN)
        println("${Number.GOLDEN_NUMBER_PHI}!!!!!!!!!!!!!!!!!!")
        assert(decimal == phi)//((duplicatedSideLength() / baseSideLength()).toFloat() == NumberConstants.GOLDEN_NUMBER_PHI.toFloat())
    }

    companion object {
        // Golden ratio: 1:phi:phi
        val duplicatedSidesRatio = Number.GOLDEN_NUMBER_PHI
        val baseRatio = 1.0
        val angleAtBaseDegrees = 72.0
        val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
            duplicatedSidesRatio,
            angleAtBaseDegrees)

        fun create(baseLength: Double): GoldenTriangle {

            // Golden ratio
            val duplicatedSidesLength = duplicatedSidesRatio * baseLength
            // baselength =
            // Get height by trigonometry
            val height = Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
                duplicatedSidesLength,
                angleAtBaseDegrees
            )
            // Set points coordinates
            // A, opposite to base, on vertical Axis
            val A = Point(x = 0.0, y = height)
            // Base BC on horizontal Axis
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)
        }

        fun createByHeight(height: Double): GoldenTriangle {
            // Golden ratio
            // From half angle A
            val hypothenuse = Trigonometry.hypotenuseLengthFromAdjacentSideAndAngle(height, 36.0 / 2.0)

            // From angle = B = 72° = angleAtBaseDegrees
            val adjacent = Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(hypothenuse, angleAtBaseDegrees)
            val baseLength = adjacent * 2
            create(baseLength)

            // Set points coordinates
            // A, opposite to base, on vertical Axis
            val A = Point(x = 0.0, y = height)
            // Base BC on horizontal Axis
            val B = Point(x = -baseLength / 2, y = 0.0)
            val C = Point(x = baseLength / 2, y = 0.0)

            return GoldenTriangle(A, B, C)

        }


    }

    var state = DecompositionModel(goldenTriangle = this)

    // iteration 1 returns P1, symP1, P2
    // Golden triangle BCP : base = CP, duplicated sides = BC and BP
    val duplicatedSidesLength = points[2].x - points[1].x

    // P1 coordinates
    val P1 = Point(
        x = points[1].x + Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0),
        y = points[1].y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLength, 36.0)
    )

    // Sym P1
    val symP1 = Symmetry.symmetryByVerticalAxis(points[0].x, P1)

    // triangle symP1, P1, P2: base = P1 P2, duplicated sides = symP1 P1 and sym P1 P2
    val duplicatedSideSymP1_P1Length = P1.x - symP1.x
    // P2 coordinates
    val P2 = Point(
        x = symP1.x
                + Trigonometry.adjacentSideLengthFromHypotenuseAndAngle(duplicatedSideSymP1_P1Length, 36.0),
        y = symP1.y
                + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(
            duplicatedSideSymP1_P1Length, 36.0
        )
    )

    val symP2 = Symmetry.symmetryByVerticalAxis(points[0].x, P2)
    val P3 = Point(
        x = points[1].x + P2.x - symP2.x,
        y = points[1].y
    )
    val symP3 = Symmetry.symmetryByVerticalAxis(points[0].x, P3)


    // Kite and Dart from losange P3 C P1 symP1
    val P4 = Point(
        x = points[0].x,
        y = points[2].y + P2.y - P1.y
    )

    // P5 Sym of P2 on horizontal axis P1 symP1
    // val P5 = Symmetry.symmetryByHorizontalAxis(P1.y, P2)
    val P5 = Point(
        x = P2.x,
        y = P1.y - (P2.y - P1.y)
    )

    val symP5 = Symmetry.symmetryByVerticalAxis(points[0].x, P5)

    // Decompose losange kite/Dart
    // Golden triangle P3 P6 symP1
    val BP3length = abs(symP2.x) + abs(P2.x)
    val P6 = Point(
        x = symP1.x + BP3length,
        y = symP1.y
    )
    // Golden triangle P6 P3 P7
    val P7 = Point(
        x = points[2].x - BP3length,
        y = points[2].y
    )
    // Golden triangle P2 P6 P2sym
    /*
    val duplicatedSidesLengthP2P6symP2 = P2.x - symP2.x
    val P6 = Point(
        x = P2.x - Trigonometry.adjacentSideLength(duplicatedSidesLengthP2P6symP2, 36.0),
        y = P2.y + Trigonometry.oppositeSideLengthFromHypotenuseAndAngle(duplicatedSidesLengthP2P6symP2, 36.0)
    )

     */

    val decompositionState = DecompositionState()

    fun arrangeSelectedModel(point: Point): Array<Decomposable> {
        decompositionState.arrangeSelectedModel(point)
        val decomposables = mutableListOf<Decomposable>()
        for (model in decompositionState.getModels()) {
            decomposables.addAll(model.decomposables())
        }
        return decomposables.toTypedArray()
    }
    fun iterate(iteration: Int, arrange: Boolean = false): Array<Decomposable> {
        // iteration 0
        if (iteration <= 0) {
            val model = DecompositionModel(this, arrayOf(this))
            decompositionState.updateModels(listOf(model))
        } else {

            // fetch the previous state
            iterate(iteration - 1, arrange)

            val actualModels = decompositionState.getModels()

            val pyramidionModel = decompositionState.getModels()[0]
            pyramidionModel.updateGoldenTriangle(getPyramidionContainer(iteration))



            if (iteration == 1) {
                val pyramidionModel = Pyramidion(getMainContainer(iteration), arrange)
                decompositionState.updateModels(listOf(pyramidionModel))
            }

            if (iteration == 2) {
                // Under symP1 P1
                // The container for the kite dart is the initial golden triangle
                val kiteDartModel =
                    if (arrange)
                        GoldenTriangleDecomposables_Dart_atBottom_sym(getMainContainer(iteration))
                    else
                        GoldenTriangleDecomposables_Kite_atBottom(getMainContainer(iteration))

                val bottomGoldenTriangleModel =
                    if(arrange)
                        GoldenTriangleDecomposables_bottom_golden_triangle(getMainContainer(iteration), position = Position.END).sym()
                    else
                        GoldenTriangleDecomposables_bottom_golden_triangle(getMainContainer(iteration), position = Position.END)


                decompositionState.updateModels(
                    listOf(
                        pyramidionModel,
                        kiteDartModel,
                        bottomGoldenTriangleModel
                    )
                )
            }

            if (iteration == 3) {
                // Kite Dart
                val modelKiteDart =
                    if (arrange)
                        decompositionState.getModels()[1].sym()
                    else decompositionState.getModels()[1]
                modelKiteDart.updateGoldenTriangle(getMainContainer(iteration))

                val bottomGoldenTriangleModel =
                    if(arrange)
                        decompositionState.getModels()[2].sym()
                    else decompositionState.getModels()[2]
                bottomGoldenTriangleModel.updateGoldenTriangle(getMainContainer(iteration))

                // Use symetry on symP1 P1 axis


                val baseKiteDart10Model = GoldenTriangleDecomposables_kite_dart_10(
                    goldenTriangle = this,
                    arrange
                )

                val symBaseGoldenTriangleModel =
                    if(arrange)
                        GoldenTriangleDecomposables_bottom_golden_triangle(
                            this,
                            arrayOf(
                                GoldenTriangleDecomposables_2AdjacentTriangles_1Gnomon(
                                    this
                                )
                            ),
                            Position.END
                        )
                else GoldenTriangleDecomposables_bottom_golden_triangle(
                    this,
                    arrayOf(GoldenTriangleDecomposables_2NonAdjacentTriangles_1Gnomon(
                        this
                    ).sym()),
                    Position.END
                )

                decompositionState.updateModels(
                    listOf(
                        pyramidionModel,
                        modelKiteDart,
                        bottomGoldenTriangleModel,
                        baseKiteDart10Model,
                        symBaseGoldenTriangleModel

                    )
                )
            }
            if (iteration >= 4) {

                actualModels[1].updateGoldenTriangle(
                    getMainContainer(iteration)
                )
                actualModels[2].updateGoldenTriangle(getMainContainer(iteration))

                actualModels[3].updateGoldenTriangle(getMainContainer(iteration))

                actualModels[4].updateGoldenTriangle(getMainContainer(iteration - 1))

                decompositionState.updateModels(
                    listOf(
                        actualModels[0],
                        actualModels[1],
                        actualModels[2],
                        actualModels[3],
                        actualModels[4]
                    )
                )
            }
        }
            return decompositionState.decomposables()
    }

    private fun getPyramidionContainer(iteration: Int): GoldenTriangle {
        var i = 1
        var container = this
        while (i < iteration) {
            container = getNextContainer(container)
            i++
        }
        return container
    }

    private fun getMainContainer(iteration: Int): GoldenTriangle {
        var i = 2
        var container = this
        i++
        while (i <= iteration ) {
            container = getNextContainer(container)
            i++
        }
        return container
    }

    fun getNextContainer(currentContainer: GoldenTriangle): GoldenTriangle {
        return GoldenTriangle(
            currentContainer.points[0],
            currentContainer.symP1,
            currentContainer.P1
        )
    }

    private fun getBottomGoldenTriangle(parentContainer: GoldenTriangle): GoldenTriangle {
        return GoldenTriangle(parentContainer.symP1, parentContainer.points[1], parentContainer.P3)
    }

    private fun symBaseGoldenTriangle(parentContainer: GoldenTriangle): GoldenTriangle{
        return GoldenTriangle(parentContainer.P1, parentContainer.symP3, parentContainer.points[2])
    }




        fun symOfTopGoldenTriangleModel(
            goldenTriangle: GoldenTriangle,
            arrange: Boolean
        ): DecomposablesModel {
            // define the sym golden triangle AA P1 symP1
            // Height of A symP1 P1 = A.y - symP1.y
            val AA = Point(
                x = goldenTriangle.points[0].x,
                y = goldenTriangle.symP1.y - (goldenTriangle.points[0].y - goldenTriangle.symP1.y)
            )
            val symGoldenTriangle_on_P1_symP1_base =
                GoldenTriangle(AA, goldenTriangle.P1, goldenTriangle.symP1)
            // get the model adapter for the sym
            val symModelAdapter = if (arrange) GoldenTriangleDecomposables_Dart_atBottom_sym(
                symGoldenTriangle_on_P1_symP1_base
            )
            else GoldenTriangleDecomposables_Kite_atBottom_sym(symGoldenTriangle_on_P1_symP1_base)
            // remove pyramidion // TODO a truncated base adapter
            val symTruncatedBaseDecomposables = symModelAdapter.decomposables().toMutableList()
                .toMutableList()
            symTruncatedBaseDecomposables.removeAt(5)

            return DecompositionModel(
                symGoldenTriangle_on_P1_symP1_base,
                symTruncatedBaseDecomposables.toTypedArray()
            )
        }
    }