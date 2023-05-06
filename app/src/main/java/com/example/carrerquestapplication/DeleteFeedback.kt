package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityDeleteDataBinding
import com.example.carrerquestapplication.databinding.ActivityDeleteFeedbackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteFeedback : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteFeedbackBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deletedataBtn.setOnClickListener {

            val feedbackId = binding.etusername.text.toString()
            if(feedbackId.isNotEmpty())
                deleteData(feedbackId)
            else
                Toast.makeText(this,"Please Enter the username", Toast.LENGTH_SHORT).show()

        }
    }

    private fun deleteData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Feedback")
        val feedbackId = binding.etusername.text.toString()
        database.child(feedbackId).removeValue().addOnSuccessListener {

            binding.etusername.text.clear()
            Toast.makeText(this,"Successfully Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }
}