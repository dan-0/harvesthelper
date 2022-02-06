package com.idleoffice.harvesthelper.testutils

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

typealias HarvestComposeTestRule = AndroidComposeTestRule<ActivityScenarioRule<TestActivity>, TestActivity>