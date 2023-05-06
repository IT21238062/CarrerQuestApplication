package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityReadFeedbackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadFeedback : AppCompatActivity() {

    private lateinit var binding : ActivityReadFeedbackBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityReadFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val feedbackId : String = binding.etusername.text.toString()
            if  (feedbackId.isNotEmpty()){

                readData(feedbackId)

            }else{

                Toast.makeText(this,"PLease enter the FeedbackId", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun readData(feedbackId: String) {

        database = FirebaseDatabase.getInstance().getReference("Feedback")
        database.child(feedbackId).get().addOnSuccessListener {

            if (it.exists()){

                val companyName = it.child("companyName").value
                val jobName = it.child("jobName").value
                val basicSalary = it.child("basicSalary").value
                val feedback = it.child("feedback").value

                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                binding.etusername.text.clear()
                binding.tvCompanyName.text = companyName.toString()
                binding.tvJobName.text = jobName.toString()
                binding.tvBasicSalary.text = basicSalary.toString()
                binding.tvFeedback.text = feedback.toString()

            }else{

                Toast.makeText(this,"Feedback Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }



    }
}