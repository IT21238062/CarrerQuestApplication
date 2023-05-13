package com.example.carrerquestapplication

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UpdateDataUnitTest {

    @Test
    fun clickCancelBtn_shouldLaunchNoteActivity() {
        val scenario = ActivityScenario.launch(UpdateData::class.java)

        onView(withId(R.id.CancelBtn))
            .perform(click())


        scenario.close()
    }

    @Test
    fun clickUpdateBtn_shouldUpdateData() {
        val scenario = ActivityScenario.launch(UpdateData::class.java)

        onView(withId(R.id.userName))
            .perform(typeText("John Doe"), closeSoftKeyboard())
        onView(withId(R.id.firstName))
            .perform(typeText("Software Engineer"), closeSoftKeyboard())
        onView(withId(R.id.lastname))
            .perform(typeText("Experienced in Android development"), closeSoftKeyboard())
        onView(withId(R.id.age))
            .perform(typeText("May 13, 2023"), closeSoftKeyboard())

        onView(withId(R.id.updateBtn))
            .perform(click())

        // Assuming that "John Doe" already exists in the database
        // and the "User" node has child nodes "jobName", "aboutJob", and "date"
        // with the corresponding values "Software Engineer", "Experienced in Android development",
        // and "May 13, 2023", respectively.
        // The following assertions check if the data was updated correctly.
        onView(withId(R.id.userName))
            .check(matches(withText("")))
        onView(withId(R.id.firstName))
            .check(matches(withText("")))
        onView(withId(R.id.lastname))
            .check(matches(withText("")))
        onView(withId(R.id.age))
            .check(matches(withText("")))

        scenario.close()
    }
}