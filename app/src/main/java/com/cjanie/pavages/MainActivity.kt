package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.tools.CanvasAdapter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PavagesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CanvasAdapter.backgroundColor,
                ) {
                    ConstraintLayoutContent()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()) {
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

        var buttonsRowHeightPx by remember {
            mutableFloatStateOf(0f)
        }

        var canvasHeightPx by remember {
            mutableFloatStateOf(0f)
        }

        var canvasWidthPx by remember {
            mutableFloatStateOf(0f)
        }

        var triangleHeightPx by remember {
            mutableFloatStateOf(0f)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    canvasWidthPx = columnWidthPx
                }
        ) {
            Canvas(modifier = Modifier) {

                val canvasAdapter = CanvasAdapter(canvasHeightPx, canvasWidthPx, triangleHeightPx)

                val square = canvasAdapter.square()
                drawPath(square.path, square.color)

                // Drawings
                val drawings = canvasAdapter.decompose(decomposeIteration, arrange)
                for (drawing in drawings) {
                    drawPath(drawing.path, drawing.color)
                }
            }
        }

        Row(
            Modifier
                .constrainAs(buttons) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(graph.start)
                    end.linkTo(graph.end)
                }
                .padding(20.dp)
                .onGloballyPositioned {
                    buttonsRowHeightPx = it.size.height.toFloat()
                    canvasHeightPx =
                            // portrait case
                        if (columnWidthPx < columnHeightPx) columnHeightPx * 2f / 3f
                        else columnHeightPx - buttonsRowHeightPx
                    // portrait case
                    triangleHeightPx =
                        if (columnWidthPx < columnHeightPx) columnWidthPx
                        // landscape case
                        else canvasHeightPx * 2f / 3f
                }
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
