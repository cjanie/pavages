package com.cjanie.pavages

import com.cjanie.pavages.logic.Number
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.logic.Point
import com.cjanie.pavages.logic.complex.Complex
import com.cjanie.pavages.tools.Trigonometry
import org.junit.Assert.assertEquals
import org.junit.Test

class GoldenTriangleTest {

    @Test(expected = AssertionError::class)
    fun `duplicated side length is in the golden ratio phi to the base side`() {
        val A = Point("A", 0.0, 2.0)
        val B = Point("B", -2.0, 0.0)
        val C = Point("C", 2.0, 0.0)
        GoldenTriangle(A, B, C)
    }

    @Test
    fun `golden ratio checked`() {
        GoldenTriangle()
    }

    @Test
    fun `create from A B points`() {
        // PHI, rapport des distances en géométrie.
        // Deux dimensions.
        // Si le rapport des 2 est égal au
        // rapport de la somme des 2 par la plus grande des 2.
        // (a + b)/a = a/b = φ, où a > b > 0
        // a/b == (a + b) / a or b (the bigest)
        val A = Point("A",0.0, 0.0)
        val B = Point("B", 0.0, Number.GOLDEN_NUMBER_PHI)
        val goldenTriangle = GoldenTriangle.createFromDuplicatedSidePoints(A, B)
        val AC = goldenTriangle.C.complex.minus(goldenTriangle.A.complex).abs()
        assertEquals(Number.GOLDEN_NUMBER_PHI, AC, 0.0)
        val BC = goldenTriangle.C.complex.minus(goldenTriangle.B.complex).abs()
        assertEquals(1.0, BC, 0.0)
    }

    @Test
    fun `create from A B points scale is correct` () {
        val duplicatedSideLength = 2.0
        val A = Point("A",0.0, 0.0)
        val B = Point("B", 0.0, duplicatedSideLength)
        GoldenTriangle.createFromDuplicatedSidePoints(A, B)
        val goldenTriangle = GoldenTriangle.createFromDuplicatedSidePoints(A, B)
        val AC = goldenTriangle.C.complex.minus(goldenTriangle.A.complex).abs()
        assertEquals(duplicatedSideLength, AC, 0.0)
        val BC = goldenTriangle.C.complex.minus(goldenTriangle.B.complex).abs()
        assertEquals(duplicatedSideLength / Number.GOLDEN_NUMBER_PHI, BC, 0.0)
    }

    @Test
    fun `create from A B points position is correct` () {

        val A = Point("A",0.0, 0.0)
        val B = Point("B", 2.0, 2.0)
        val duplicatedSideLength = B.complex.minus(A.complex).abs()

        val rotationAngleDegrees =
            Trigonometry.angleDegreesFromHypothenuseAndOppositeLengths(duplicatedSideLength, B.x)

        //assertEquals(45.0, rotationAngleDegrees, 0.0)
        val goldenTriangle = GoldenTriangle.createFromDuplicatedSidePoints(A, B)
        val AC = goldenTriangle.C.complex.minus(goldenTriangle.A.complex).abs()
        assertEquals(duplicatedSideLength.toFloat(), AC.toFloat(), 0f)
        val BC = goldenTriangle.C.complex.minus(goldenTriangle.B.complex).abs()
        assertEquals(duplicatedSideLength / Number.GOLDEN_NUMBER_PHI, BC, 0.0)

    }
}