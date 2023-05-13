package com.example.carrerquestapplication
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.gms.tasks.Tasks
import com.google.common.base.Verify.verify
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.junit.Assert.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import androidx.test.rule.ActivityTestRule





class AddDataUnitTest {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var firebaseDatabase: FirebaseDatabase

    @Test
    fun testCancelButton() {
        val scenario = ActivityScenario.launch(AddData::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.CancelBtn)).perform(ViewActions.click())
        scenario.close()
    }


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val activityRule = ActivityScenarioRule(AddData::class.java)


    @Before
    fun setUp() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setPersistenceEnabled(true)
    }

    @After
    fun tearDown() {
        firebaseDatabase.reference.child("User").removeValue()
    }

    @Test
    fun testAddData() {
        val activityScenario = ActivityScenario.launch(AddData::class.java)
        activityScenario.onActivity { activity ->
            val database = Firebase.database.reference

            val jobName = "Test Job Name"
            val aboutJob = "Test About Job"
            val date = "Test Date"
            val userName = "Test User Name"

            val user = User(jobName, aboutJob, date, userName)

            database.child("User").child(userName).setValue(user).addOnSuccessListener {
                val reference = database.child("User").child(userName)

                val dataSnapshot = Tasks.await(reference.get())

                assertEquals(dataSnapshot.child("jobName").getValue(String::class.java), jobName)
                assertEquals(dataSnapshot.child("aboutJob").getValue(String::class.java), aboutJob)
                assertEquals(dataSnapshot.child("date").getValue(String::class.java), date)
                assertEquals(dataSnapshot.child("userName").getValue(String::class.java), userName)

                Toast.makeText(activity, "Test passed", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(activity, "Test failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @get:Rule
    val activityTestRule = ActivityTestRule(AddData::class.java)

    @Test
    fun registerBtn_click_should_add_user_data_to_database() {
        // Arrange
        val activity = activityTestRule.activity
        val jobName = "Software Engineer"
        val aboutJob = "Develop software applications"
        val date = "01/01/2022"
        val userName = "testuser123"
        activity.runOnUiThread {
            activity.binding.firstName.setText(jobName)
            activity.binding.lastName.setText(aboutJob)
            activity.binding.age.setText(date)
            activity.binding.userName.setText(userName)
        }

        // Act
        activity.runOnUiThread {
            activity.binding.registerBtn.performClick()
        }

        // Assert
        val userRef = database.getReference("User").child(userName)
        val snapshot = userRef.getSnapshotBlocking()
        assertEquals(snapshot.child("jobName").getValue(String::class.java), jobName)
        assertEquals(snapshot.child("aboutJob").getValue(String::class.java), aboutJob)
        assertEquals(snapshot.child("date").getValue(String::class.java), date)
    }





    fun DatabaseReference.getSnapshotBlocking(): DataSnapshot {
        val task = this.get()
        Tasks.await(task)
        return task.result!!
    }



}



