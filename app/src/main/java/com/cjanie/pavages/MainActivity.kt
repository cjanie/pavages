package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.tools.DrawTools
import com.cjanie.pavages.ui.tools.CanvasGraph2DAdapter

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
                    // For column size depending on screen size
                    var columnHeightPx by remember {
                        mutableFloatStateOf(0f)
                    }
                    var columnWidthPx by remember {
                        mutableFloatStateOf(0f)
                    }
                    
                    var decomposeIteration by remember {
                        mutableIntStateOf(0)
                    }

                    var stepText by remember {
                        mutableStateOf("Initial")
                    }
                    
                    Row {
                        Button(onClick = { decomposeIteration -= 1 }) {
                            Text("Back")
                        }

                        Button(onClick = { decomposeIteration += 1 }) {
                            Text("Forward")
                        }
                    }
                    Row {
                        Text(stepText)
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            // Set the column size using the layout coordinates
                            .onGloballyPositioned {
                                columnHeightPx = it.size.height.toFloat()
                                columnWidthPx = it.size.width.toFloat()
                            }
                    ) {
                        val canvasSizePx =
                            if (columnWidthPx < columnHeightPx) columnWidthPx else columnHeightPx
                        val textMeasurer = rememberTextMeasurer()

                        fun drawText(drawScope: DrawScope, text: String, offset: Offset) {
                            val textLayoutResult = DrawTools.getTextResultLayout(text, textMeasurer)
                            drawScope.drawText(textLayoutResult, Color.Red, DrawTools.getTextOffset(textLayoutResult, offset))

                        }

                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val strokeWidth = 1F

                            fun drawText(text: String, offset: Offset) {
                                drawText(this, text, offset)
                            }
                            // Draw Graph (horizontal and vertical axis, center O
                            val canvasAdapter = CanvasGraph2DAdapter(canvasSizePx)

                            val horizontalAxis = canvasAdapter.horizontalAxis

                            drawLine(Color.Blue, horizontalAxis[0], horizontalAxis[1], strokeWidth)

                            val verticalAxis = canvasAdapter.verticalAxis
                            drawLine(Color.Blue, verticalAxis[0], verticalAxis[1], strokeWidth)

                            val center = canvasAdapter.center
                            drawText("O", center)

                            val drawing = canvasAdapter.goldenTriangle
                            drawPath(drawing.path, drawing.color)

                            val drawings = canvasAdapter.decompose(decomposeIteration)
                            for (drawing in drawings) {
                                drawPath(drawing.path, drawing.color)
                            }

                            // http://www.debart.fr/1s/pentagone.mobile.html
                            // Steps to draw a pentagon


                            //val pentagon = Pentagon(circleRadius.toDouble())
                            /*
                            // Steps to draw a pentagon
                            // CircleEnclosure
                            val circleEnclosure = pentagon.circleEnclosure()
                            drawCircle(Color.Black, circleEnclosure.radius.toFloat(), graph2D.pointToOffset(circleEnclosure.center))
                            drawText(this,circleEnclosure.center.name, graph2D.pointToOffset(circleEnclosure.center))

                            // VerticalDiameter
                            val verticalAxis = pentagon.verticalAxis()
                            drawLine(Color.White, graph2D.pointToOffset(verticalAxis.top), graph2D.pointToOffset(verticalAxis.bottom))
                            drawText(verticalAxis.top.name, graph2D.pointToOffset(verticalAxis.top))
                            drawText(verticalAxis.bottom.name, graph2D.pointToOffset(verticalAxis.bottom))

                            // HorizontalRadius
                            val horizontalRadius = pentagon.horizontalRadius()
                            drawLine(Color.White, graph2D.pointToOffset(horizontalRadius.left), graph2D.pointToOffset(horizontalRadius.center))
                            drawText(horizontalRadius.left.name, graph2D.pointToOffset(horizontalRadius.left))

                            // IntermediateCircle
                            val intermediateCircle = pentagon.intermediateCircle()
                            drawCircle(Color.White, intermediateCircle.radius.toFloat(), graph2D.pointToOffset(intermediateCircle.center), style = Stroke(strokeWidth))
                            drawText(intermediateCircle.center.name, graph2D.pointToOffset(intermediateCircle.center))
                            drawText(intermediateCircle.top.name, graph2D.pointToOffset(intermediateCircle.top))
                            drawLine(Color.White, graph2D.pointToOffset(intermediateCircle.center), graph2D.pointToOffset(horizontalRadius.left))

                            // horizontal diagonal
                            val pentagonHorizontalDiagonal = pentagon.pentagonHorizontalDiagonal()
                            drawLine(Color.White, graph2D.pointToOffset(pentagonHorizontalDiagonal.left), graph2D.pointToOffset(pentagonHorizontalDiagonal.right))
                            drawText(pentagonHorizontalDiagonal.left.name, graph2D.pointToOffset(pentagonHorizontalDiagonal.left))
                            drawText(pentagonHorizontalDiagonal.right.name, graph2D.pointToOffset(pentagonHorizontalDiagonal.right))
                            drawText(pentagonHorizontalDiagonal.center.name, graph2D.pointToOffset(pentagonHorizontalDiagonal.center))

                            // Inner circle
                            val innerCircle = pentagon.innerCircle()
                            drawCircle(Color.White, innerCircle.radius.toFloat(), graph2D.pointToOffset(innerCircle.center), style = Stroke(strokeWidth))
                            drawText(innerCircle.center.name, graph2D.pointToOffset(innerCircle.center))
                            drawText(innerCircle.bottom.name, graph2D.pointToOffset(innerCircle.bottom))

                            // pentagon bottom side
                            val pentagonBottomSide = pentagon.pentagonBottomSide()
                            drawText(pentagonBottomSide.left.name, graph2D.pointToOffset(pentagonBottomSide.left))
                            drawText(pentagonBottomSide.right.name, graph2D.pointToOffset(pentagonBottomSide.right))

                            val pentagonOffsets = graph2D.pointsToOffsets(pentagon.pentagonPoints)
                            for (i in 0 ..pentagonOffsets.size - 2) {
                                drawLine(Color.Green, pentagonOffsets[i], pentagonOffsets[i+1])
                            }
                            drawLine(Color.Green, pentagonOffsets[pentagonOffsets.size - 1], pentagonOffsets[0])


                            val goldenTriangle = pentagon.goldenTriangle
                            val triangleOffsets = graph2D.pointsToOffsets(pentagon.triangle)
                            val trianglePath = DrawTools.createPath(triangleOffsets)
                            drawPath(trianglePath, Color.Blue)
                            */
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