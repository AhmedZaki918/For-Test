package com.example.fortest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ActivityInputOutputTest {

    private lateinit var scenario: ActivityScenario<MainActivity>


    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun activityLaunch() {
        // Check a visibility of view
        onView(withId(R.id.et_message)).check(matches(isDisplayed()))
        // Check a text on view is existing and match the text in string.xml file
        onView(withId(R.id.btn_save)).check(matches(withText(R.string.go)))
        onView(withId(R.id.btn_save)).perform(click())
    }

    @Test
    fun testNavigation_toSecondActivity() {
        onView(withId(R.id.btn_save)).perform(click())
        onView(withId(R.id.tv_welcome)).check(matches(isDisplayed()))
    }
}