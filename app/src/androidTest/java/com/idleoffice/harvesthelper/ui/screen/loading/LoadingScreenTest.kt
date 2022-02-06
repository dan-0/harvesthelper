package com.idleoffice.harvesthelper.ui.screen.loading

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
class LoadingScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun happyPath() {
        composeTestRule.setContent {
            LoadingScreen()
        }

        verifyLoadingScreenVisible(composeTestRule)
    }

    companion object {

        fun verifyLoadingScreenVisible(composeTestRule: HarvestComposeTestRule) {
            val loadingSTring = composeTestRule.activity.getString(R.string.loading)
            composeTestRule.onNodeWithText(loadingSTring)
                .assertIsDisplayed()
        }

    }

}