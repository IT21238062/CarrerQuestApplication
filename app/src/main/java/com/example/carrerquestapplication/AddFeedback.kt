package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityAddFeedbackBinding
import com.example.carrerquestapplication.databinding.ActivityAdddataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddFeedback : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivityAddFeedbackBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {

            val feedbackId = binding.FeedbackId.text.toString()
            val companyName = binding.CompanyName.text.toString()
            val jobName = binding.JobName.text.toString()
            val basicSalary = binding.BasicSalary.text.toString()
            val feedback = binding.Feedback.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Feedback")
            val Feedback = Feedback(feedbackId,companyName,jobName,basicSalary,feedback)
            database.child(feedbackId).setValue(Feedback).addOnSuccessListener {

                binding.FeedbackId.text.clear()
                binding.CompanyName.text.clear()
                binding.JobName.text.clear()
                binding.BasicSalary.text.clear()
                binding.Feedback.text.clear()

                Toast.makeText(this,"Successfully Created", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


            }
        }
    }
}