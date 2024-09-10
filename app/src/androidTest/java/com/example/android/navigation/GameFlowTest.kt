package com.example.android.navigation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GameFlowTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun gameFlowTest() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.playButton), withText("Play"),
                childAtPosition(
                    allOf(
                        withId(R.id.titleConstraint),
                        childAtPosition(
                            withId(R.id.myNavHostFragment),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.secondAnswerRadioButton), withText("NavController"),
                childAtPosition(
                    allOf(
                        withId(R.id.questionRadioGroup),
                        childAtPosition(
                            withId(R.id.frameLayout),
                            2
                        )
                    ),
                    1
                )
            )
        )
        appCompatRadioButton.perform(scrollTo(), click())

        val appCompatRadioButton2 = onView(
            allOf(
                withId(R.id.secondAnswerRadioButton), withText("NavController"),
                childAtPosition(
                    allOf(
                        withId(R.id.questionRadioGroup),
                        childAtPosition(
                            withId(R.id.frameLayout),
                            2
                        )
                    ),
                    1
                )
            )
        )
        appCompatRadioButton2.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.submitButton), withText("Submit"),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.submitButton), withText("Submit"),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        appCompatButton3.perform(scrollTo(), click())

        val appCompatRadioButton3 = onView(
            allOf(
                withId(R.id.firstAnswerRadioButton), withText("ViewGroup"),
                childAtPosition(
                    allOf(
                        withId(R.id.questionRadioGroup),
                        childAtPosition(
                            withId(R.id.frameLayout),
                            2
                        )
                    ),
                    0
                )
            )
        )
        appCompatRadioButton3.perform(scrollTo(), click())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.submitButton), withText("Submit"),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        appCompatButton4.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
