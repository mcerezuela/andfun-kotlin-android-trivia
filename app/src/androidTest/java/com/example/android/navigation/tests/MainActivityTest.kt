package com.example.android.navigation.tests


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.android.navigation.pageobjects.AboutScreen
import com.example.android.navigation.pageobjects.DrawerScreen
import com.example.android.navigation.pageobjects.GameOverScreen
import com.example.android.navigation.pageobjects.GameScreen
import com.example.android.navigation.pageobjects.GameWonScreen
import com.example.android.navigation.pageobjects.PlayScreen
import com.example.android.navigation.pageobjects.RulesScreen
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest : TestBase() {
    private val playScreen = PlayScreen()
    private val drawerScreen = DrawerScreen()
    private val aboutScreen = AboutScreen()
    private val rulesScreen = RulesScreen()
    private val gameScreen = GameScreen()
    private val gameWonScreen = GameWonScreen()
    private val gameOverScreen = GameOverScreen()

    @Test
    fun quizTitleIsReadyTest() {
        //playObject.clickOnStartQuiz()
        assertThat("Play button should be displayed", playScreen.isViewDisplayed(), `is`(true))
        assertThat(
            "Drawer should be available",
            playScreen.isNavigationDrawerButtonDisplayed(),
            `is`(true)
        )
        assertThat(
            "Overflow menu button should be displayed",
            playScreen.isOverflowMenuButtonDisplayed(),
            `is`(true)
        )
    }

    @Test
    fun drawerOptionsTest() {
        playScreen.clickOnNavigationDrawerButton()
        assertThat("Rules should be available", drawerScreen.isRulesOptionDisplayed(), `is`(true))
        assertThat("About should be available", drawerScreen.isAboutOptionDisplayed(), `is`(true))
    }

    @Test
    fun drawerAboutNavigateBackToPlayTest() {
        playScreen.clickOnNavigationDrawerButton()
        drawerScreen.clickOnAboutButton()
        assertThat(
            "About Image should be Displayed",
            aboutScreen.isAboutImageDisplayed(),
            `is`(true)
        )
        assertThat(
            "About  Text should be Displayed",
            aboutScreen.isAboutTextDisplayed(),
            `is`(true)
        )
        assertThat(
            "About  Title should be Displayed",
            aboutScreen.isAboutTitleDisplayed(),
            `is`(true)
        )
        assertThat(
            "About  Title return to main Displayed",
            aboutScreen.isReturnToPageDisplayed(),
            `is`(true)
        )
        aboutScreen.clickOnNavigateUpButton()
        assertThat("Play button should be displayed", playScreen.isViewDisplayed(), `is`(true))
    }

    @Test
    fun drawerRulesNavigateBackToPlayTest() {
        playScreen.clickOnNavigationDrawerButton()
        drawerScreen.clickOnRulesButton()
        assertThat(
            "Rules Image should be Displayed",
            rulesScreen.isRulesImageDisplayed(),
            `is`(true)
        )
        assertThat(
            "Rules Text should be Displayed",
            rulesScreen.isRulesTextDisplayed(),
            `is`(true)
        )
        assertThat(
            "Rule Title return to main Displayed",
            rulesScreen.isReturnToPageDisplayed(),
            `is`(true)
        )
        rulesScreen.clickOnNavigateUpButton()
        assertThat(
            "Play button should be displayed",
            playScreen.isViewDisplayed(),
            `is`(true)
        )

    }

    @Test
    fun completePlayTest() {
        playScreen.clickOnStartQuiz()
        assertThat(
            "Game image should be displayed",
            gameScreen.isQuestionImageDisplayed(),
            `is`(true)
        )
        gameScreen.selectCorrectAnswer1()
        gameScreen.selectCorrectAnswer2()
        gameScreen.selectCorrectAnswer3()
        assertThat(
            "You win Image should be displayed",
            gameWonScreen.isyouWinImageDisplayed(),
            `is`(true)
        )
    }

    @Test
    fun incorrectAnswerThenTryAgainTest() {
        playScreen.clickOnStartQuiz()
        gameScreen.selectCorrectAnswer1()
        gameScreen.selectCorrectAnswer2()
        gameScreen.selectInCorrectAnswer()
        assertThat(
            "Game Over Image should be displayed",
            gameOverScreen.isGameOverImageDisplayed(),
            `is`(true)
        )
        gameOverScreen.clickOnTryAgainButton()
        assertThat(
            "Game image should be displayed",
            gameScreen.isQuestionImageDisplayed(),
            `is`(true)
        )
    }

}
