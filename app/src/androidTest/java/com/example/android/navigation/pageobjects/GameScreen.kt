package com.example.android.navigation.pageobjects

import android.view.View
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

class GameScreen : PageObjectBase() {

    data class QuestionCorrectAnswers(
        val text: String,
        val answer: String
    )

    private val questionCorrectAnswers: MutableList<QuestionCorrectAnswers> = mutableListOf(
        QuestionCorrectAnswers(text = "What is Android Jetpack?", answer = "all of these"),
        QuestionCorrectAnswers(text = "Base class for Layout?", answer = "ViewGroup"),
        QuestionCorrectAnswers(text = "Layout for complex Screens?", answer = "ConstraintLayout"),
        QuestionCorrectAnswers(text = "Pushing structured data into a Layout?", answer = "Data Binding"),
        QuestionCorrectAnswers(text = "Inflate layout in fragments?", answer = "onCreateView"),
        QuestionCorrectAnswers(text = "Build system for Android?", answer = "Gradle"),
        QuestionCorrectAnswers(text = "Android vector format?", answer = "VectorDrawable"),
        QuestionCorrectAnswers(text = "Android Navigation Component?", answer = "NavController"),
        QuestionCorrectAnswers(text = "Registers app with launcher?", answer = "intent-filter"),
        QuestionCorrectAnswers(text = "Mark a layout for Data Binding?", answer = "<layout>")
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

    fun clickOnSubmitButton() {
        onView(withId(submitButton)).perform(click())
    }

    private fun correctAnwser() {
        val question = getQuestionText()
        val answer = getCorrectAnswer(question)
        if (answer != null) {
            selectCorrectRadioButton(answer)
        }
    }

    private fun getCorrectAnswer(question: String): String? {
        return questionCorrectAnswers.find { it.text == question }?.answer
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
    private fun selectCorrectRadioButton(answer: String) {
        // Find the radio button within the RadioGroup that matches the answer text
        onView(
            allOf(
                isDescendantOfA(withId(questionRadioGroup)), // Ensures the radio button is within the RadioGroup
                withText(answer) // Matches the radio button with the answer text
            )
        ).perform(click()) // Click on the correct radio button
    }

}