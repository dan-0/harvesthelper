package com.idleoffice.harvesthelper.ui.screen.error

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.idleoffice.harvesthelper.R
import com.idleoffice.harvesthelper.testutils.HarvestComposeTestRule
import com.idleoffice.harvesthelper.testutils.TestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ErrorScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun happyPath() {
        composeTestRule.setContent {
            ErrorScreen()
        }

        verifyErrorScreenVisible(composeTestRule)
    }

    companion object {

        fun verifyErrorScreenVisible(composeTestRule: HarvestComposeTestRule) {
            val errorString = composeTestRule.activity.getString(R.string.error)
            composeTestRule.onNodeWithText(errorString)
                .assertIsDisplayed()
        }

    }

}