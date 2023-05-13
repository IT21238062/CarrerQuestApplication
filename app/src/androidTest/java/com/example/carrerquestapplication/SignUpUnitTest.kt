package com.example.carrerquestapplication

import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches



@RunWith(AndroidJUnit4::class)
class SignUpUnitTest {

    private lateinit var scenario: ActivityScenario<SignUp>

    @Before
    fun setup() {
        // Start the activity under test
        scenario = ActivityScenario.launch(SignUp::class.java)
    }

    @Test
    fun signUp_emptyFields() {
        // Check that an error is displayed for each empty field
        onView(withId(R.id.signUpBtn)).perform(click())
        onView(withId(R.id.signUpName)).check(matches(hasErrorText("Enter Your Name")))
        onView(withId(R.id.signUpEmail)).check(matches(hasErrorText("Enter Your Email address")))
        onView(withId(R.id.signUpPhone)).check(matches(hasErrorText("Enter Your Phone Number")))
        onView(withId(R.id.signUpCPasswordLayout)).check(matches(hasErrorText("ReEnter the Password")))
    }

    @Test
    fun signUp_invalidEmail() {
        // Check that an error is displayed for an invalid email
        val email = "invalidemail"
        onView(withId(R.id.signUpEmail)).perform(replaceText(email), closeSoftKeyboard())
        onView(withId(R.id.signUpBtn)).perform(click())
        onView(withId(R.id.signUpEmail)).check(matches(hasErrorText("Enter a valid Email address")))
        onView(withId(R.id.signUpProgressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun signUp_invalidPhone() {
        // Check that an error is displayed for an invalid phone number
        val phone = "12345"
        onView(withId(R.id.signUpPhone)).perform(replaceText(phone), closeSoftKeyboard())
        onView(withId(R.id.signUpBtn)).perform(click())
        onView(withId(R.id.signUpPhone)).check(matches(hasErrorText("Enter a valid Phone number")))
        onView(withId(R.id.signUpProgressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun signUp_shortPassword() {
        // Check that an error is displayed for a short password
        val password = "1234567"
        onView(withId(R.id.signUpPassword)).perform(replaceText(password), closeSoftKeyboard())
        onView(withId(R.id.signUpCPassword)).perform(replaceText(password), closeSoftKeyboard())
        onView(withId(R.id.signUpBtn)).perform(click())
        onView(withId(R.id.signUpProgressBar)).check(matches(not(isDisplayed())))
    }
}