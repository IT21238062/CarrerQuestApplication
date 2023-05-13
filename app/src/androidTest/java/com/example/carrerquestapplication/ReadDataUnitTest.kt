package com.example.carrerquestapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class ReadDataUnitTest {

    class ReadDataTest {

        @get:Rule
        val activityRule = ActivityScenarioRule(ReadData::class.java)

        @Test
        fun clickCancelButton_opensNoteActivity() {
            // Start the activity scenario
            val activityScenario = ActivityScenario.launch(ReadData::class.java)

            // Click the cancel button
            Espresso.onView(ViewMatchers.withId(R.id.CancelBtn)).perform(ViewActions.click())

            // Check if Note activity is opened
            Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.default_activity_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        @Test
        fun clickReadDataButton_withoutUsername_showsToastMessage() {
            // Start the activity scenario
            val activityScenario = ActivityScenario.launch(ReadData::class.java)

            // Click the read data button without entering username
            Espresso.onView(ViewMatchers.withId(R.id.readdataBtn)).perform(ViewActions.click())

            // Check if toast message is displayed
            Espresso.onView(ViewMatchers.withText("Please enter the Username"))
                .inRoot(ToastMatcher()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}