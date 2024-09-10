package com.example.android.navigation.pageobjects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.navigation.R

class GameWonScreen: PageObjectBase() {
    private val youWinImage = R.id.youWinImage
    private val nextMatchButton = R.id.nextMatchButton

    fun isyouWinImageDisplayed(): Boolean {
        return try {
            onView(withId(youWinImage)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }
}