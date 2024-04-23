package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cjanie.pavages.tools.TrigonometryTools
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.tools.DrawTools
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PavagesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {
                        Row(Modifier.padding(20.dp)) {
                            Greeting(name = "Penrose Pavage")
                        }
                        Row {
                            Canvas(modifier = Modifier) {
                                val strokeWidth = 1F

                                // http://www.debart.fr/1s/pentagone.mobile.html
                                val circleRadius = 300F
                                // center
                                val o = Offset(circleRadius, circleRadius)
                                // On Horizontal axis
                                val a = Offset(o.x, 0F)
                                val aa = Offset(o.x, o.y + circleRadius)

                                // On Vertical axis
                                val bb = Offset(o.x - circleRadius, o.y)

                                // Measure
                                val o_bb = circleRadius
                                val o_k = circleRadius / 2F
                                val k_bb = TrigonometryTools.hypotenuseLengthFromPythagoreTheorem(o_bb.toDouble(), o_k.toDouble())

                                val k = Offset(o.x, o.y + o_k)
                                val u = Offset(o.x, k.y - k_bb.toFloat())

                                val o_u = o.y - u.y
                                val o_i = o_u / 2.0
                                val b_i = sqrt(circleRadius.pow(2) - o_i.pow(2))

                                val i = Offset(o.x, o.y - o_i.toFloat())
                                val b = Offset(i.x - b_i.toFloat(), i.y)
                                val e = Offset(i.x + b_i.toFloat(), i.y)

                                val o_p = o_k / 2F
                                val p = Offset(o.x, o.y + o_p)
                                val i_p = p.y - i.y
                                val j = Offset(o.x, p.y + i_p)

                                val o_j = j.y - o.y
                                val o_c = circleRadius
                                val j_c = sqrt(o_c.pow(2) - o_j.pow(2))
                                val c = Offset(j.x - j_c, j.y)
                                val d = Offset(j.x + j_c, j.y)

                                drawCircle(Color.Black, circleRadius, o)
                                drawLine(Color.White, a, aa)
                                drawLine(Color.White, bb, o)
                                drawLine(Color.White, k, bb)
                                drawCircle(Color.White, k_bb.toFloat(), k, style = Stroke(strokeWidth))
                                drawLine(Color.White, i, b)
                                drawLine(Color.White, i, e)
                                drawLine(Color.White, p, Offset(p.x - i_p, p.y))
                                drawCircle(Color.White, i_p, p, style = Stroke(strokeWidth))
                                drawLine(Color.Green, a, b)
                                drawLine(Color.Green, a, e)
                                drawLine(Color.Green, b, c)
                                drawLine(Color.Green, c, d)
                                drawLine(Color.Green, d, e)

                                val trianglePath = DrawTools.createPath(arrayOf(a, c, d))
                                drawPath(trianglePath, Color.Blue)
                            }
                        }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        PavagesTheme {
            Greeting("Android")
        }
    }
}