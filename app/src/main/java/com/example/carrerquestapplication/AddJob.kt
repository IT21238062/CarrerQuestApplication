package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityAddJobBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddJob : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivityAddJobBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.button3.setOnClickListener {

            val jobName = binding.editTextTextPersonName.text.toString()
            val quality = binding.editTextTextPersonName2.text.toString()
            val age = binding.editTextTextPersonName3.text.toString()
            val description = binding.editTextTextPersonName5.text.toString()
            val gender = binding.radioButton.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Job")
            val Job = Job(jobName,quality,age,description,gender)
            database.child(jobName).setValue(Job).addOnSuccessListener {

                binding.editTextTextPersonName.text.clear()
                binding.editTextTextPersonName2.text.clear()
                binding.editTextTextPersonName3.text.clear()
                binding.editTextTextPersonName5.text.clear()


                Toast.makeText(this,"Successfully Created", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()


            }


        }
    }
}