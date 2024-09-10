package com.example.android.navigation.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.navigation.R
import org.hamcrest.Matchers.allOf

class RulesScreen : PageObjectBase() {

    private val aboutImage = R.id.rulesImage
    private val rulesText = R.id.rulesText

    fun clickOnNavigateUpButton() {
        getNavigateUpButton().perform(click())
    }

    fun isRulesImageDisplayed(): Boolean {
        return try {
            onView(withId(aboutImage)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun isRulesTextDisplayed(): Boolean {
        return try {
            onView(withId(rulesText)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }
    fun isReturnToPageDisplayed(): Boolean {
        return try {
            getNavigateUpButton()
            true
        } catch (e: Exception) {
            false
        }

    }

    private fun getNavigateUpButton() = onView(
        allOf(
            withContentDescription("Navigate up"),
            childAtPosition(
                allOf(
                    withId(androidx.constraintlayout.widget.R.id.action_bar),
                    childAtPosition(
                        withId(androidx.constraintlayout.widget.R.id.action_bar_container),
                        0
                    )
                ),
                1
            ),
            isDisplayed()
        )
    )
}