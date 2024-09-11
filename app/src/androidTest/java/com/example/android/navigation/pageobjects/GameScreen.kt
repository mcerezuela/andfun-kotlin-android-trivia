package com.example.android.navigation.pageobjects

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

import com.example.android.navigation.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class GameScreen : PageObjectBase() {

    data class QuestionCorrectAnswers(
        val text: String,
        val answer: String,
        val incorrect: String
    )

    private val questionCorrectAnswers: MutableList<QuestionCorrectAnswers> = mutableListOf(
        QuestionCorrectAnswers(text = "What is Android Jetpack?", answer = "all of these",incorrect = "libraries"),
        QuestionCorrectAnswers(text = "Base class for Layout?", answer = "ViewGroup",incorrect = "ViewRoot"),
        QuestionCorrectAnswers(text = "Layout for complex Screens?", answer = "ConstraintLayout",incorrect = "FrameLayout"),
        QuestionCorrectAnswers(text = "Pushing structured data into a Layout?", answer = "Data Binding",incorrect = "OnClick"),
        QuestionCorrectAnswers(text = "Inflate layout in fragments?", answer = "onCreateView",incorrect = "onInflateLayout"),
        QuestionCorrectAnswers(text = "Build system for Android?", answer = "Gradle",incorrect = "Groyle"),
        QuestionCorrectAnswers(text = "Android vector format?", answer = "VectorDrawable",incorrect = "AndroidVector"),
        QuestionCorrectAnswers(text = "Android Navigation Component?", answer = "NavController",incorrect = "NavSwitcher"),
        QuestionCorrectAnswers(text = "Registers app with launcher?", answer = "intent-filter",incorrect = "app-launcher"),
        QuestionCorrectAnswers(text = "Mark a layout for Data Binding?", answer = "<layout>",incorrect = "<dbinding>")
    )

    private val questionImage = R.id.questionImage
    private val questionText = R.id.questionText
    private val questionRadioGroup = R.id.questionRadioGroup
    private val submitButton = R.id.submitButton

    fun isQuestionImageDisplayed(): Boolean {
        return try {
            onView(withId(questionImage)).check(matches(isDisplayed()))
            true
        } catch (e: Exception) {
            false // Menu option is not displayed or an exception occurred
        }
    }

    fun selectCorrectAnswer1() {
        correctAnwser()
        clickOnSubmitButton()
    }
    fun selectCorrectAnswer2() {
        correctAnwser()
        clickOnSubmitButton()
    }
    fun selectCorrectAnswer3() {
        correctAnwser()
        clickOnSubmitButton()
    }

    fun selectInCorrectAnswer() {
        incorrectAnwser()
        clickOnSubmitButton()
    }

    fun clickOnSubmitButton() {
        onView(withId(submitButton)).perform(click())
    }

    private fun correctAnwser() {
        val question = getQuestionText()
        val answer = getCorrectAnswer(question)
        if (answer != null) {
            selectRadioButton(answer)
        }
    }

    private fun incorrectAnwser() {
        val question = getQuestionText()
        val answer = getIncorrectAnswer(question)
        if (answer != null) {
            selectRadioButton(answer)
        }
    }

    private fun getCorrectAnswer(question: String): String? {
        return questionCorrectAnswers.find { it.text == question }?.answer
    }

    private fun getIncorrectAnswer(question: String): String? {
        return questionCorrectAnswers.find { it.text == question }?.incorrect
    }

    private fun getQuestionText(): String {
        return getTextFromView(withId(questionText))
    }

    private fun getTextFromView(matcher: Matcher<View>): String {
        val stringHolder = arrayOf<String?>(null)
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                // Ensure it is a TextView or subclass
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val tv = view as TextView // Cast the view to a TextView
                stringHolder[0] = tv.text.toString() // Retrieve the text
            }
        })
        return stringHolder[0] ?: "" // Return the text or an empty string
    }
    private fun selectRadioButton(answer: String) {
        // Find the radio button within the RadioGroup that matches the answer text
        onView(
            allOf(
                isDescendantOfA(withId(questionRadioGroup)), // Ensures the radio button is within the RadioGroup
                withText(answer) // Matches the radio button with the answer text
            )
        ).perform(click()) // Click on the correct radio button
    }


    private fun atPosition(matcher: Matcher<View>, position: Int): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                matcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && matcher.matches(parent) && view == parent.getChildAt(position)
            }
        }
    }

}