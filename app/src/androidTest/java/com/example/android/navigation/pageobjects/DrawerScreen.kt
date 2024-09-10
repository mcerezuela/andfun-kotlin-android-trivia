package com.example.android.navigation.pageobjects

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.navigation.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class DrawerScreen : PageObjectBase() {
    private val aboutButton = R.id.aboutFragment
    private val ruleButton = R.id.rulesFragment

    fun isRulesOptionDisplayed(): Boolean {
        return try {
            onView(withId(ruleButton)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun isAboutOptionDisplayed(): Boolean {
        return try {
            onView(withId(aboutButton)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun clickOnRulesButton() {
        onView(withId(ruleButton)).perform(click())
    }

    fun clickOnAboutButton() {
        onView(withId(aboutButton)).perform(click())
    }
}
