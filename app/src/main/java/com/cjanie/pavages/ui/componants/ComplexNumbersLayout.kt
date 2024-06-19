package com.cjanie.pavages.ui.componants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cjanie.pavages.logic.complex.Complex

interface SetComplexNumbers {
    fun set(numbers: List<Complex>)
}

interface SetOperations {
    fun set(operators: List<String>)
}
@Composable
fun ComplexNumbersComposable() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {

        var complexNumbers by remember {
            mutableStateOf(emptyList<Complex>())
        }

        val setComplexNumbers: SetComplexNumbers = object: SetComplexNumbers {
            override fun set(numbers: List<Complex>) {
                complexNumbers = numbers
            }
        }

        ComplexNumbersLayout(setComplexNumbers, complexNumbers)
    }
}
@Composable
fun ComplexNumbersLayout(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
    
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),

    ) {

        // Create references for the composables to constrain
        val (board, graph) = createRefs()

        Row(
            Modifier
                .constrainAs(board) {
                    top.linkTo(parent.top)
                    start.linkTo(graph.start)
                    end.linkTo(graph.end)
                }
        ) {
            Board(setComplexNumbers = setComplexNumbers, complexNumbers = complexNumbers)
        }

        // For column size depending on screen size
        var columnHeightPx by remember {
            mutableFloatStateOf(0f)
        }
        var columnWidthPx by remember {
            mutableFloatStateOf(0f)
        }

        var canvasWidthPx by remember {
            mutableFloatStateOf(0f)
        }

        Column(
            modifier = Modifier
                .constrainAs(graph) {
                    top.linkTo(board.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                // Set the column size using the layout coordinates
                .onGloballyPositioned {
                    columnHeightPx = it.size.height.toFloat()
                    columnWidthPx = it.size.width.toFloat()
                    canvasWidthPx = columnWidthPx
                }
        ) {
            val textMeasurer = rememberTextMeasurer()

            val commonComplexNumbers = listOf(Complex.ZERO, Complex.ONE, Complex.i)

            val style = TextStyle(
                fontSize = 32.sp,
                color = Color.Black,
            )

            val mapComplexToTextResultLayout = mutableMapOf<Complex, TextLayoutResult>()
            for (c in commonComplexNumbers) {

                val (x, y) = c
                val text = "(${coordinateAsString(x)}, ${coordinateAsString(y)})"
                val textLayoutResult = remember(text, style) {
                    textMeasurer.measure(text, style)
                }
                mapComplexToTextResultLayout.put(c, textLayoutResult)
            }

            // Canvas
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {

                val scale = 300f

                drawPoints(
                    points = listOf(
                        complexToOffset(Complex.i, scale, center),
                        complexToOffset(Complex.ZERO, scale, center),
                        complexToOffset(Complex.ONE, scale, center)
                    ),
                    pointMode = PointMode.Points,
                    cap = StrokeCap.Round,
                    color = Color.Red,
                    strokeWidth = 25f
                )

                for (complex in mapComplexToTextResultLayout.keys) {
                    val offset = complexToOffset(complex, scale, center)
                    val textLayoutResult = mapComplexToTextResultLayout.get(complex)
                    if(textLayoutResult != null) {
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = offset
                        )

                    }
                }

                for (c in complexNumbers) {
                    // Point
                    drawPoints(
                        listOf(complexToOffset(c, scale, center)),
                        pointMode = PointMode.Points,
                        cap = StrokeCap.Round,
                        color = Color.Green,
                        strokeWidth = 25f
                    )

                    // Text
                    val (x, y) = c
                    val text = "(${coordinateAsString(x)}, ${coordinateAsString(y)})"
                    var textLayoutResult = textMeasurer.measure(text, style)
                    drawText(textLayoutResult,
                        topLeft = complexToOffset(c, scale, center))
                }

            }
        }
    }

}

fun coordinateAsString(double: Double): String {
    return if(double.toInt().compareTo(double) == 0)
        double.toInt().toString()
    else double.toString()
}
fun complexToOffset(c: Complex, scale: Float, center: Offset): Offset {
    // unit 1 = scale
    val (x, y) = c
    return Offset(center.x + x.toFloat() * scale, center.y - y.toFloat() * scale)
}

@Composable
fun Board(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
    if(complexNumbers.isEmpty()) {
        EditComplexNumber(setComplexNumbers, complexNumbers)
    } else {
        Calculator(setComplexNumbers, complexNumbers)
    }
}

@Composable
fun EditComplexNumber(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
    ConstraintLayout(
        modifier = Modifier.background(Color.Green)
    ) {
        val (title, example, edit) = createRefs()
        // Title
        Text("Complex Number",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
            }
        )
        // Example
        Text("x + yi",
            fontSize = 32.sp,
            modifier = Modifier
                .constrainAs(example) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                }
            )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.constrainAs(edit) {
                top.linkTo(example.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(title.start)
                end.linkTo(title.end)
            }
        ) {
            var xValue by rememberSaveable { mutableStateOf("") }
            var yValue by rememberSaveable { mutableStateOf("") }

            fun updateComplexNumbers() {
                if(xValue.isNotEmpty() && yValue.isNotEmpty()) {
                    val list = mutableListOf<Complex>()
                    list.addAll(complexNumbers)
                    list.add(Complex(xValue.toDouble(), yValue.toDouble()))
                    setComplexNumbers.set(list.toList())
                    xValue = ""
                    yValue = ""
                }
            }

            updateComplexNumbers()

            //
            val keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
            ) {
                Text("x = ",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                )
                TextField(
                    value = xValue,
                    onValueChange = {
                        xValue = it
                    },
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1.5f),
                    keyboardOptions = keyboardOptions,
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
            ) {
                Text("y = ",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                )
                TextField(
                    value = yValue,
                    onValueChange = {
                        yValue = it
                    },
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1.5f),
                    keyboardOptions = keyboardOptions,
                )
            }

        }
    }
}

@Composable
fun Calculator(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
    var calculationText by remember {
        mutableStateOf("")
    }

    var operations by remember {
        mutableStateOf(emptyList<String>())
    }

    var updatedText = ""

    for (i in complexNumbers.indices) {
        val (x, y) = complexNumbers[i]
        val text = "(${coordinateAsString(x)}, ${coordinateAsString(y)})"
        updatedText += text
        if(operations.isNotEmpty() && operations.size > i) {
            updatedText += " ${operations[i]} "
        }
    }

    calculationText = updatedText

    ConstraintLayout {
        val (screen, operators, edition) = createRefs()

        Row(Modifier
            .constrainAs(screen) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            ) {
            Screen(text = calculationText)
        }

        Row(
            modifier = Modifier.constrainAs(operators) {
                top.linkTo(screen.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) {
            val setOperations = object : SetOperations {
                override fun set(operators: List<String>) {
                    operations = operators
                }
            }
            Operators(setOperations = setOperations, operations = operations, setComplexNumbers, complexNumbers)
        }
        Row(
            modifier = Modifier.constrainAs(edition) {
                top.linkTo(operators.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {

            EditComplexNumber(setComplexNumbers = setComplexNumbers, complexNumbers = complexNumbers)

        }
    }
}

@Composable
fun Screen(text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue)
    ) {
        Text(text,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
            )
    }
}

@Composable
fun Operators(setOperations: SetOperations, operations: List<String>, setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
    val operators = listOf("+", "-", "*", "/")

    val updatedOperations = mutableListOf<String>()
    updatedOperations.addAll(operations)
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(16.dp),
    ) {
        for (operator in operators) {
            Button(
                onClick = {
                    updatedOperations.add(operator)
                    setOperations.set(updatedOperations)
                    },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(operator,
                    fontSize = 32.sp)
            }
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            EnterButton(setComplexNumbers, complexNumbers, operations)
        }


    }
}

@Composable
fun EnterButton(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>, operations: List<String>) {
    Button(
        onClick = {
            if (complexNumbers.size > 1 && operations.isNotEmpty()) {
                val first = complexNumbers[complexNumbers.lastIndex - 1]
                val second = complexNumbers.last()

                if (operations.last() == "+") {
                    val updatedComplexNumbers = mutableListOf<Complex>()
                    updatedComplexNumbers.addAll(complexNumbers)
                    updatedComplexNumbers.add(first + second)
                    setComplexNumbers.set(updatedComplexNumbers.toList())
                }
            }
        },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            "=",
            fontSize = 32.sp
        )
    }
}