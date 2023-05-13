package com.example.carrerquestapplication


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.function.Predicate.not
import java.util.regex.Pattern.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*



class SignInUnitTeat {

    @RunWith(AndroidJUnit4::class)
    class SignInTest {

        @get:Rule
        val activityRule = ActivityScenarioRule(SignIn::class.java)

        @Test
        fun signInBtn_click_should_show_progress_bar() {
            // Arrange
            val signInEmail = "test@example.com"
            val signInPassword = "test123"

            // Act
            onView(withId(R.id.signInEmail)).perform(typeText(signInEmail), closeSoftKeyboard())
            onView(withId(R.id.signInPassword)).perform(typeText(signInPassword), closeSoftKeyboard())
            onView(withId(R.id.signInBtn)).perform(click())

            // Assert
            onView(withId(R.id.signInProgressBar)).check(matches(isDisplayed()))
        }

        @Test
        fun signInBtn_click_with_invalid_email_should_show_error() {
            // Arrange
            val signInEmail = "invalidemail"
            val signInPassword = "test123"

            // Act
            onView(withId(R.id.signInEmail)).perform(typeText(signInEmail), closeSoftKeyboard())
            onView(withId(R.id.signInPassword)).perform(typeText(signInPassword), closeSoftKeyboard())
            onView(withId(R.id.signInBtn)).perform(click())

            // Assert
            onView(withId(R.id.signInEmail)).check(matches(hasErrorText("Enter a valid Email address")))
        }

        @Test
        fun signInBtn_click_with_short_password_should_show_error() {
            // Arrange
            val signInEmail = "test@example.com"
            val signInPassword = "test"

            // Act
            onView(withId(R.id.signInEmail)).perform(typeText(signInEmail), closeSoftKeyboard())
            onView(withId(R.id.signInPassword)).perform(typeText(signInPassword), closeSoftKeyboard())
            onView(withId(R.id.signInBtn)).perform(click())

            // Assert
            onView(withId(R.id.signInPassword)).check(matches(hasErrorText("Enter password more than 8 characters")))
        }

        @Test
        fun signInBtn_click_with_empty_fields_should_show_error() {
            // Arrange
            val signInEmail = ""
            val signInPassword = ""

            // Act
            onView(withId(R.id.signInEmail)).perform(typeText(signInEmail), closeSoftKeyboard())
            onView(withId(R.id.signInPassword)).perform(typeText(signInPassword), closeSoftKeyboard())
            onView(withId(R.id.signInBtn)).perform(click())

            // Assert
            onView(withId(R.id.signInEmail)).check(matches(hasErrorText("Enter Your Email address")))
            onView(withId(R.id.signInPassword)).check(matches(hasErrorText("Enter your Password")))
            onView(withId(R.id.signInProgressBar)).check(matches((isDisplayed())))
        }

    }
}