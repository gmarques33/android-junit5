package de.mannodermaus.junit5.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ComposeExtensionTests {

    @JvmField
    @RegisterExtension
    val extension = createComposeExtension()

    @ValueSource(
        strings = [
            "click me",
            "touch me",
            "jfc it actually works"
        ]
    )
    @ParameterizedTest
    fun test(buttonLabel: String) {
        extension.setContent {
            Box {
                var counter by remember { mutableStateOf(0) }

                Text(text = "Clicked: $counter")
                Button(onClick = { counter++ }) {
                    Text(text = buttonLabel)
                }
            }
        }

        extension.onNodeWithText("Clicked: 0").assertIsDisplayed()
        extension.onNodeWithText(buttonLabel).performClick()
        extension.onNodeWithText("Clicked: 1").assertIsDisplayed()
    }
}