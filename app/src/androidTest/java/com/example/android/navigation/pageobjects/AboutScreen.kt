package com.example.android.navigation.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.navigation.R
import org.hamcrest.Matchers.allOf

class AboutScreen : PageObjectBase() {
    private val drawerLayout = R.id.drawerLayout
    private val aboutImage = R.id.aboutImage
    private val rulesText = R.id.rulesText

    fun clickOnNavigateUpButton() {
        getNavigateUpButton().perform(click())
    }

    fun isAboutImageDisplayed(): Boolean {
        return try {
            onView(withId(aboutImage)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun isAboutTextDisplayed(): Boolean {
        return try {
            onView(withId(rulesText)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun isAboutTitleDisplayed(): Boolean {
        return try {
            onView(
                allOf(
                    withText("About Trivia"),
                    withParent(
                        allOf(
                            withId(androidx.constraintlayout.widget.R.id.action_bar),
                            withParent(withId(androidx.constraintlayout.widget.R.id.action_bar_container))
                        )
                    ),
                    isDisplayed()
                )
            ).check(matches(withText("About Trivia")))
            true
        } catch (e: Exception) {
            false
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