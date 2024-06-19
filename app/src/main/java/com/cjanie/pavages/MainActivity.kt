package com.cjanie.pavages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cjanie.pavages.ui.theme.PavagesTheme
import com.cjanie.pavages.ui.componants.ComplexNumbersComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PavagesTheme {
                ComplexNumbersComposable()
            }
        }
    }
}
