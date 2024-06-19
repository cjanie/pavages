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

interface SetCalculationResult {
    fun set(c: Complex?)
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

        var calculationResult by remember {
            mutableStateOf<Complex?>(null)
        }

        val setCalculationResult = object: SetCalculationResult {
            override fun set(c: Complex?) {
                calculationResult = c
            }

        }

        ComplexNumbersLayout(setComplexNumbers, complexNumbers, setCalculationResult, calculationResult)
    }
}
@Composable
fun ComplexNumbersLayout(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>, setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
    
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),

    ) {

        val (board, graph) = createRefs()

        Row(
            Modifier
                .constrainAs(board) {
                    top.linkTo(parent.top)
                    start.linkTo(graph.start)
                    end.linkTo(graph.end)
                }
        ) {
            Board(setComplexNumbers = setComplexNumbers, complexNumbers = complexNumbers, setCalculationResult = setCalculationResult, calculationResult = calculationResult)
        }

        Row(
            modifier = Modifier
                .constrainAs(graph) {
                    top.linkTo(board.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Graph(complexNumbers, calculationResult)
        }
    }

}

@Composable
fun Board(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>, setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
    if(complexNumbers.isEmpty()) {
        ComplexNumberEditor(setComplexNumbers, complexNumbers)
    } else {
        Calculator(setComplexNumbers, complexNumbers, setCalculationResult, calculationResult)
    }
}

@Composable
fun ComplexNumberEditor(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>) {
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
fun Calculator(setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>, setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
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

    if(calculationResult != null) {
        val (x, y) = calculationResult
        updatedText += " = (${coordinateAsString(x)}, ${coordinateAsString(y)})"
    }

    calculationText = updatedText

    ConstraintLayout {
        val (screen, keyboard) = createRefs()

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
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(keyboard) {
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
            Keyboard(setOperations = setOperations, operations = operations, setComplexNumbers = setComplexNumbers, complexNumbers = complexNumbers, setCalculationResult = setCalculationResult, calculationResult = calculationResult)
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
fun Keyboard(setOperations: SetOperations, operations: List<String>, setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>, setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
    if(operations.size < complexNumbers.size) {
        OperatorsAndEnterButtonsBar(setOperations, operations, setComplexNumbers, complexNumbers, setCalculationResult, calculationResult)
    } else {
        ComplexNumberEditor(setComplexNumbers = setComplexNumbers, complexNumbers = complexNumbers)
    }
}
@Composable
fun OperatorsAndEnterButtonsBar(setOperations: SetOperations, operations: List<String>, setComplexNumbers: SetComplexNumbers, complexNumbers: List<Complex>,
                                setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.weight(4f)
        ) {
            Operators(setOperations = setOperations, operations = operations, setCalculationResult = setCalculationResult, calculationResult = calculationResult, setComplexNumbers = setComplexNumbers)
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            EnterButton(setCalculationResult, complexNumbers, operations)
        }
    }
}
@Composable
fun Operators(setOperations: SetOperations, operations: List<String>,setComplexNumbers: SetComplexNumbers, setCalculationResult: SetCalculationResult, calculationResult: Complex?) {
    val operators = listOf("+", "-", "*", "/")

    val updatedOperations = mutableListOf<String>()
    updatedOperations.addAll(operations)
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(0.dp, 16.dp, 0.dp, 16.dp)
    ) {
        for (operator in operators) {
            Button(
                onClick = {
                    if(calculationResult != null) {
                        setComplexNumbers.set(listOf(calculationResult))
                        setCalculationResult.set(null)
                        setOperations.set(listOf(operator))
                    } else {
                        updatedOperations.add(operator)
                        setOperations.set(updatedOperations)
                    }
                          },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(operator,
                    fontSize = 32.sp)
            }
        }
    }
}

@Composable
fun EnterButton(setCalculationResult: SetCalculationResult, complexNumbers: List<Complex>, operations: List<String>) {
    Row(
        Modifier
            .background(Color.Magenta)
            .padding(0.dp, 16.dp, 0.dp, 16.dp)
    ) {
        Button(
            onClick = {
                if (complexNumbers.size > 1 && operations.isNotEmpty()) {
                    val first = complexNumbers[complexNumbers.lastIndex - 1]
                    val second = complexNumbers.last()

                    if (operations.last() == "+") {
                        setCalculationResult.set(first + second)
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
}

@Composable
fun Graph(complexNumbers: List<Complex>, calculationResult: Complex?) {
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

        if(calculationResult != null) {
            // Point
            drawPoints(
                listOf(complexToOffset(calculationResult, scale, center)),
                pointMode = PointMode.Points,
                cap = StrokeCap.Round,
                color = Color.Magenta,
                strokeWidth = 25f
            )
            // Text
            val (x, y) = calculationResult
            val text = "(${coordinateAsString(x)}, ${coordinateAsString(y)})"

            val resultTextStyle = TextStyle(
                fontSize = 32.sp,
                color = Color.Magenta,
            )

            var textLayoutResult = textMeasurer.measure(text, resultTextStyle)
            drawText(textLayoutResult,
                topLeft = complexToOffset(calculationResult, scale, center))
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