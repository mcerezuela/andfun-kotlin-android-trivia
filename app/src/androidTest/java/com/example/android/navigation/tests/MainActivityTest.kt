package com.example.android.navigation.tests


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.navigation.pageobjects.DrawerScreen
import com.example.android.navigation.pageobjects.PlayScreen
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest: TestBase() {
    private val playScreen = PlayScreen()
    private val drawerScreen = DrawerScreen()

    @Test
    fun quizTitleIsReadyTest() {
        //playObject.clickOnStartQuiz()
        assertThat("Play button should be displayed", playScreen.isViewDisplayed(), `is`(true))
        assertThat("Drawer should be available", playScreen.isNavigationDrawerButtonDisplayed(), `is`(true))
        assertThat("Overflow menu button should be displayed", playScreen.isOverflowMenuButtonDisplayed(), `is`(true))
    }
    @Test
    fun drawerOptionsTest() {
        playScreen.clickOnNavigationDrawerButton()
        assertThat("Rules should be available", drawerScreen.isRulesOptionDisplayed(), `is`(true))
        assertThat("About should be available", drawerScreen.isAboutOptionDisplayed(), `is`(true))
    }
    @Test
    fun drawerAboutTest() {
        playScreen.clickOnNavigationDrawerButton()
        drawerScreen.clickOnAboutButton()
    }

}
