package com.idleoffice.harvesthelper.ui.component.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.input.TextFieldValue
import androidx.test.platform.app.InstrumentationRegistry
import com.idleoffice.harvesthelper.R
import org.junit.Rule
import org.junit.Test

class SearchViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialState() {
        composeTestRule.setContent {
            SearchView(
                state = remember {
                    mutableStateOf(TextFieldValue(""))
                }
            )
        }

        composeTestRule.onNodeWithTag("searchView")
            .assertTextEquals("")

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.search))
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(context.getString(R.string.close))
            .assertDoesNotExist()
    }

    @Test
    fun verifyClearIconDisplayed() {

        val testText = "test text"
        composeTestRule.setContent {
            SearchView(
                state = remember {
                    mutableStateOf(TextFieldValue(""))
                }
            )
        }

        composeTestRule.onNodeWithTag("searchView")
            .performTextInput(testText)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.close))
            .performClick()

        composeTestRule.onNodeWithTag("searchView")
            .assertTextEquals("")

    }

}