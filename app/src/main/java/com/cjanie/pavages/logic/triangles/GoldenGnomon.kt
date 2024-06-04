package com.cjanie.pavages.logic.triangles

import com.cjanie.pavages.logic.Decomposable
import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.Point

class GoldenGnomon(
    pointOppositeToBase: Point,
    basePoint1: Point,
    basePoint2: Point
): IsoscelesTriangle(pointOppositeToBase, basePoint1, basePoint2),
    Decomposable {

    val A = points[0]
    val B = points[1]
    val C = points[2]
    companion object {
        val goldenRatio = Number.GOLDEN_NUMBER_PHI
    }

    enum class DecompositionModel {
        ONE_TRIANGLE_TWO_GNOMONS,
        ONE_TRIANGLE_ONE_GNOMON,
        ONE_TRIANGLE_ONE_GNOMON_SYM,
    }
    fun decompose(model: DecompositionModel): Array<Decomposable> {
        when (model) {
            DecompositionModel.ONE_TRIANGLE_TWO_GNOMONS -> return decompose()
            DecompositionModel.ONE_TRIANGLE_ONE_GNOMON -> return decompose1Triangle1Gnomon()
            DecompositionModel.ONE_TRIANGLE_ONE_GNOMON_SYM -> return decompose1Triangle1GnomonSym()
        }
    }

    override fun decompose() : Array<Decomposable> {
        val (qX, qY) = B.complex + (A.complex - B.complex) / goldenRatio
        val (rX, rY) = B.complex + (C.complex - B.complex) / goldenRatio
        val Q = Point("Q", qX, qY)
        var R = Point("R", rX, rY)

        // GoldenTriangle
        // RAQ
        // 2 Golden Gnomons
        // QBR, RCA
        return arrayOf(
            GoldenTriangle(R, Q, A),
            GoldenGnomon(Q, R, B),
            GoldenGnomon(R, C, A)
        )
    }

    private fun decompose1Triangle1Gnomon(): Array<Decomposable> {
        val (rX, rY) = B.complex + (C.complex - B.complex) / goldenRatio
        var R = Point("R", rX, rY)
        return arrayOf(
            GoldenTriangle(B, R, A),
            GoldenGnomon(R, C, A),
        )
    }

    private fun decompose1Triangle1GnomonSym(): Array<Decomposable> {
        val (rX, rY) = C.complex + (B.complex - C.complex) / goldenRatio
        var R = Point("R", rX, rY)
        return arrayOf(
            GoldenTriangle(C, A, R),
            GoldenGnomon(R, A, B),
        )
    }
    override fun decompose(arrange: Boolean): Array<Decomposable> {
        val (rX, rY) = B.complex + (C.complex - B.complex) / goldenRatio
        var R = Point("R", rX, rY)

        if(arrange)
            // 1 triangle + 1 gnomon
            return arrayOf(
                GoldenTriangle(B, R, A),
                GoldenGnomon(R, C, A)
            )

        else return decompose(DecompositionModel.ONE_TRIANGLE_TWO_GNOMONS)

    }

    init {
        // check that the duplicated side is in the golden ration 1 / phi to the base side
        //assert((duplicatedSideLength() / baseSideLength()).toFloat() == (1 / Number.GOLDEN_NUMBER_PHI).toFloat())
    }
}