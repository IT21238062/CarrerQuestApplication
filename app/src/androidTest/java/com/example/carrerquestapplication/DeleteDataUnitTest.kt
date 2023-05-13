package com.example.carrerquestapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class DeleteDataUnitTest {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var deleteData: DeleteData

    @Mock
    lateinit var database: DatabaseReference

    @Before
    fun setUp() {
        deleteData = DeleteData()
        deleteData.database = database
    }

    @Test
    fun testCancelButton() {
        val scenario = ActivityScenario.launch(DeleteData::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.CancelBtn)).perform(ViewActions.click())
        scenario.close()
    }

    @Test
    fun delete_Data_should_remove_value_from_firebasedatabase () {
        // Given
        val userName = "testUser"

        // When
        deleteData.deleteData(userName)

        // Then
        verify(database).child(userName).removeValue()
    }

    @Test
    fun deleteData_should_clear_text_and_show_success_message_on_success() {
        // Given
        val userName = "testUser"

        // When
        deleteData.deleteData(userName)

        // Then
        verify(database).child(userName).removeValue()
        verify(deleteData.binding.etusername).text.clear()

    }

    @Test
    fun deleteData_should_show_error_message_on_failure () {
        // Given
        val userName = "testUser"
        val exception = Exception()

        doAnswer {


        }.`when`(database).child(userName).removeValue(any(DatabaseReference.CompletionListener::class.java))

        // When
        deleteData.deleteData(userName)

        // Then
        verify(database).child(userName).removeValue(any(DatabaseReference.CompletionListener::class.java))

    }

    }