package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.tools.DrawTools
import com.cjanie.pavages.ui.tools.CanvasAdapter

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
                    ConstraintLayoutContent()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        // Create references for the composables to constrain
        val (graph, buttons) = createRefs()

        var decomposeIteration by remember {
            mutableIntStateOf(0)
        }

        var arrange by remember {
            mutableStateOf(false)
        }

        // For column size depending on screen size
        var columnHeightPx by remember {
            mutableFloatStateOf(0f)
        }
        var columnWidthPx by remember {
            mutableFloatStateOf(0f)
        }

        Column(
            modifier = Modifier
                .constrainAs(graph) {
                    top.linkTo(parent.top)
                    bottom.linkTo(buttons.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
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

            Canvas(modifier = Modifier.fillMaxHeight(canvasSizePx)) {
                val strokeWidth = 1F

                fun drawText(text: String, offset: Offset) {
                    drawText(this, text, offset)
                }

                val canvasAdapter = CanvasAdapter(canvasSizePx)
                // Draw Graph (horizontal and vertical axis, center O)
                val horizontalAxis = canvasAdapter.horizontalAxis
                drawLine(Color.Blue, horizontalAxis[0], horizontalAxis[1], strokeWidth)

                val verticalAxis = canvasAdapter.verticalAxis
                drawLine(Color.Blue, verticalAxis[0], verticalAxis[1], strokeWidth)

                val center = canvasAdapter.center()
                drawText("O", center)

                // Drawings
                val drawings = canvasAdapter.decompose(decomposeIteration, arrange)
                for (drawing in drawings) {
                    drawPath(drawing.path, drawing.color)
                }

            }

        }

        Row(Modifier
            .constrainAs(buttons) {
                bottom.linkTo(parent.bottom)
                start.linkTo(graph.start)
                end.linkTo(graph.end)
            }.padding(20.dp)
        ) {
            Button(
                onClick = { decomposeIteration -= 1 },
                modifier = Modifier.padding(8.dp)


            ) {
                Text("-")
            }

            Button(onClick = { decomposeIteration += 1 },
                modifier = Modifier.padding(8.dp)) {
                Text("+")
            }
            Button(onClick = { arrange = !arrange },
                modifier = Modifier.padding(8.dp)) {
                Text("Arrange")
            }
        }
    }
}

@Composable
fun Buttons() {

}