package com.example.android.navigation.tests

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.navigation.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

open class TestBase {
    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
}