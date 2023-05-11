package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityUpdateDataBinding
import com.example.carrerquestapplication.databinding.ActivityUpdateFeedBackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateFeedBack : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateFeedBackBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val feedBackID = binding.feedBackID.text.toString()
            val companyName = binding.companyName.text.toString()
            val jobName = binding.jobName.text.toString()
            val basicSalary = binding.basicSalary.text.toString()
            val feedBack = binding.feedback.text.toString()

            updateData(feedBackID,companyName,jobName,basicSalary,feedBack)

        }
    }

    private fun updateData(feedBackID: String, companyName: String, jobName: String,basicSalary: String,feedBack:String) {

        database = FirebaseDatabase.getInstance().getReference("Feedback")
        val user = mapOf<String,String>(
            "feedBackID" to feedBackID,
            "companyName" to companyName,
            "jobName" to jobName,
            "basicSalary" to basicSalary,
            "feedBack" to feedBack
        )

        database.child(feedBackID).updateChildren(user).addOnSuccessListener {

            binding.feedBackID.text.clear()
            binding.companyName.text.clear()
            binding.jobName.text.clear()
            binding.basicSalary.text.clear()
            binding.feedback.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}
}