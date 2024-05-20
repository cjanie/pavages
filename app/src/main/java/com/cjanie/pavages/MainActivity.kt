package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.CanvasAdapter
import kotlin.random.Random

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

        var tap by remember {
            mutableStateOf(Offset.Zero)
        }


        var clickableRects by remember {
            mutableStateOf(listOf<Rect>())
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
            Canvas(modifier = Modifier
                .fillMaxSize() // Same as surface for the click to work
                // https://dev.to/lex_fury/touch-interactions-in-jetpack-compose-5be9
                .pointerInput(key1 = Unit) {
                    detectTapGestures(
                        onTap = { tapOffset ->
                            tap = tapOffset
                        }
                    )
                }

            ) {

                val canvasAdapter = CanvasAdapter(canvasHeightPx, canvasWidthPx, triangleHeightPx)

                val square = canvasAdapter.square()
                drawPath(square.path, square.color)

                // Drawings
                val drawings = canvasAdapter.decompose(decomposeIteration, arrange)
                for (drawing in drawings) {
                    drawPath(drawing.path, drawing.color)
                }

                if(canvasAdapter.isPointInGoldenTriangle(tap)) {
                    clickableRects = drawings.map { it.path.getBounds() }
                    val rnd = Random.Default
                    val colors = listOf(
                        // Predefined colors like
                        Color.Green,
                        Color.Black,
                        Color.Red,
                        Color.Yellow,
                        Color.Cyan,
                        Color.Magenta,
                        Color.White,
                        //Custom color hex:
                        Color(0xFFF0670A),
                        //Custom color RGB
                        Color(12, 154, 224, 255),
                        Color(241, 7, 230, 255),
                        Color(146, 130, 116, 255),
                        Color(0, 255, 179, 255)
                    )
                        for (i in clickableRects.indices) {


                            drawRect(colors[rnd.nextInt(colors.size - 1)], clickableRects[i].topLeft, clickableRects[i].size)

                        }

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
