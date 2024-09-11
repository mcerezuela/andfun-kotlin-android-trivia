package com.example.android.navigation.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.navigation.R

class GameOverScreen : PageObjectBase() {
    private val gameOverImage = R.id.gameOverFragment
    private val tryAgainButton = R.id.tryAgainButton

    fun isGameOverImageDisplayed(): Boolean {
        return try {
            onView(withId(gameOverImage)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun clickOnTryAgainButton() {
        onView(withId(tryAgainButton)).perform(click())
    }
}