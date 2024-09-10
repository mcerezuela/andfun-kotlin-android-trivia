package com.example.android.navigation.pageobjects

import android.view.View

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import com.example.android.navigation.R

import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf


class PlayScreen: PageObjectBase() {
    private val playButton = R.id.playButton
    private val navigationDrawerButtonDescription = "Open navigation drawer"

    // Function to click on the Play Button
    fun clickOnStartQuiz() {
        onView(withId(playButton)).perform(click())
    }
    // Function to click on the navigation drawer button
    fun clickOnNavigationDrawerButton() {
        getNavigationDrawerButton().perform(click())
    }

    // Function to click on the overflow menu button
    fun clickOverflowMenuButton() {
        onView(getOverflowMenuButtonMatcher()).perform(click())
    }

    fun isViewDisplayed(): Boolean {
        return try {
            onView(withId(playButton)).check(matches(isDisplayed()))
            true // View is displayed
        } catch (e: Exception) {
            false // View is not displayed or an exception occurred
        }
    }

    // Function to check if the navigation drawer button is displayed
    fun isNavigationDrawerButtonDisplayed(): Boolean {
        return try {
            getNavigationDrawerButton().check(matches(isDisplayed()))
            true // Navigation drawer button is displayed
        } catch (e: Exception) {
            false // Navigation drawer button is not displayed or an exception occurred
        }
    }


    // Function to check if the overflow menu button is displayed
    fun isOverflowMenuButtonDisplayed(): Boolean {
        return try {
            onView(getOverflowMenuButtonMatcher()).check(matches(isDisplayed()))
            true // Button is displayed
        } catch (e: Exception) {
            false // Button is not displayed or an exception occurred
        }
    }

    // Private function to get the navigation drawer button matcher
    private fun getNavigationDrawerButton() = onView(
        allOf(
            withContentDescription(navigationDrawerButtonDescription),
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
    // Helper function to get the matcher for the overflow menu button
    private fun getOverflowMenuButtonMatcher(): Matcher<View> {
        return allOf(
            withContentDescription("More options"),
            childAtPosition(
                childAtPosition(
                    withId(androidx.constraintlayout.widget.R.id.action_bar),
                    2
                ),
                0
            ),
            isDisplayed()
        )
    }
}