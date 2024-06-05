package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.cjanie.pavages.logic.triangles.CustomModel
import com.cjanie.pavages.logic.triangles.GoldenGnomon
import com.cjanie.pavages.logic.triangles.GoldenTriangle
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.CanvasAdapter

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

@OptIn(ExperimentalMaterial3Api::class)
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
            mutableStateOf(GoldenTriangle.DecomposableModel.TRIANGLE_2_GNOMON_3)
        }

        var arrangeCustom by remember {
            mutableStateOf<CustomModel?>(null)
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
                var drawings = if(arrangeCustom == null)
                    canvasAdapter.decompose(decomposeIteration, arrange)
                else canvasAdapter.decompose(decomposeIteration, arrangeCustom!!)
                for (drawing in drawings) {
                    drawPath(drawing.path, drawing.color)
                }

                if(canvasAdapter.isPointInGoldenTriangle(tap)) {
                    drawCircle(CanvasAdapter.backgroundColor, 200f, tap, 0.2f)
                    //arrange = !arrange
                    //canvasAdapter.arrange(tap)
                    tap = Offset.Zero
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

            fun changeModel(actual: GoldenTriangle.DecomposableModel): GoldenTriangle.DecomposableModel {
                val models = GoldenTriangle.DecomposableModel.values()
                var next = models[0]
                for(i in 0..models.size - 1) {
                    if (models[i] == actual) {
                        if(i == models.size - 1) {
                            next = models[0]
                        } else {
                            next = models[i + 1]
                        }
                        break
                    }
                }
                return next
            }

            Button(onClick = { arrange = changeModel(arrange) },
                modifier = Modifier.padding(8.dp)) {
                Text("Arrange")
            }

            val openAlertDialog = remember { mutableStateOf(false) }
            Button(onClick = {
                openAlertDialog.value = !openAlertDialog.value
                arrangeCustom = CustomModel(
                //GoldenTriangle.DecomposableModel.ADJACENT_TRIANGLE_2_GNOMON_1,
                GoldenTriangle.DecomposableModel.NON_ADJACENT_TRIANGLE_2_GNOMON_1,
                //GoldenGnomon.DecomposableModel.ONE_TRIANGLE_TWO_GNOMONS,
                GoldenGnomon.DecomposableModel.ONE_TRIANGLE_ONE_GNOMON_SYM
            ) },
                modifier = Modifier.padding(8.dp)) {
                Text("Try")
            }

            CustomDialog(openAlertDialog.value)
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog() {
    AlertDialog(
        onDismissRequest = {  },
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)

            ) {
                Text(text = "Select a decomposition model for Golden Triangles")
                Row {
                    for (model in GoldenTriangle.DecomposableModel.entries) {
                        Canvas(modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp)
                            .background(Color.Magenta)) {

                            val canvasAdapter = CanvasAdapter(size.height, size.width, size.height/2)

                            val drawings = canvasAdapter.decompose(1, model)
                            for (drawing in drawings) {
                                drawPath(drawing.path, drawing.color)
                            }
                        }
                    }
                }
                Text(text = "Select a decomposition model for Golden Gnomons")
                Row {
                    for (model in GoldenGnomon.DecomposableModel.entries) {
                        Canvas(modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp)
                            .background(Color.Red)) {
                            val canvasAdapter = CanvasAdapter(size.height, size.width, size.height/2)
                            val drawings = canvasAdapter.decomposeGoldenGnomon(model)
                            for (drawing in drawings) {
                                drawPath(drawing.path, drawing.color)
                            }

                        }
                    }
                }
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Apply")
                    }
                }
            }

    }
}

@Composable
fun CustomDialog(openAlertDialog: Boolean) {


    when {
        // ...
        openAlertDialog -> {
            CustomAlertDialog()
        }
    }
}
