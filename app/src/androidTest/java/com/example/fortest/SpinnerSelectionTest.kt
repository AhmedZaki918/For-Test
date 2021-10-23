package com.example.fortest


import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SpinnerSelectionTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private val appContext = ApplicationProvider.getApplicationContext<Context>()


    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun iterateSpinnerItems() {
        // Get an array from resources
        val array = appContext.resources
            .getStringArray(R.array.labels_array)

        for (i in array.indices) {
            // Find the spinner and click on it.
            onView(withId(R.id.labels_spinner)).perform(click())
            // Find the spinner item and click on it.
            onData(`is`(array[i])).perform(click())

            /** Find the text view and check that the spinner item
             * is part of the string.
             */
            onView(withId(R.id.tv_phone))
                .check(matches(withText(containsString(array[i]))))
        }
    }
}