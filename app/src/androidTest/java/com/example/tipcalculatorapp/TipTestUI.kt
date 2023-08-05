package com.example.tipcalculatorapp

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TipTestUI {

    @get: Rule
    val composeTestRule = createComposeRule()
    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        composeTestRule.setContent {
            TipCalculatorAppTheme {
                TipCalculator()
            }
        }
        composeTestRule.
        onNodeWithText(text = "Bill Amount")
            .performTextInput("20")

        composeTestRule.
        onNodeWithText(text = "Percentage")
            .performTextInput("20")

        composeTestRule.
        onNodeWithText(text = "Tip Amount: $4.0")
            .assertExists("No node with this text was found.")

    }


}