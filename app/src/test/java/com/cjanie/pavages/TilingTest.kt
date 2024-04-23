package com.cjanie.pavages

import com.cjanie.pavages.logic.EuclideanTiling
import junit.framework.TestCase
import org.junit.Test

class TilingTest {

    @Test(expected = AssertionError::class)
    fun `the motive of the tiling (polygon) has at minimun three sides`() {
        EuclideanTiling(2)
    }

    @Test
    fun `six triangles around summit A`() {
        // a triangle is three-sided polygon
        // a tiling of triangles is Tiling(3)
        TestCase.assertEquals(6, EuclideanTiling(3).k)
    }

    @Test
    fun `four squares around summit A`() {
        // a square is four-sided polygon
        // a tiling of squares is Tiling(4)
        TestCase.assertEquals(4, EuclideanTiling(4).k)
    }

    @Test
    fun `three hexagons around summit A`() {
        TestCase.assertEquals(3, EuclideanTiling(6).k)
    }

}