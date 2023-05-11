package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityAddApplicationBinding
import com.example.carrerquestapplication.databinding.ActivityAdddataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class AddApplication : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivityAddApplicationBinding
    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnAddDetails.setOnClickListener {

            val name = binding.etCourseName.text.toString()
            val job = binding.etCourseSuitedFor.text.toString()
            val quality = binding.etCourseImageLink.text.toString()
            val salary = binding.etCourseLink.text.toString()
            val description = binding.etCourseDescription.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Application")
            val Application = Application( name, job,quality,salary,description)
            database.child(name).setValue(Application).addOnSuccessListener {

                binding.etCourseName.text?.clear()
                binding.etCourseSuitedFor.text?.clear()
                binding.etCourseImageLink.text?.clear()
                binding.etCourseLink.text?.clear()
                binding.etCourseDescription.text?.clear()

                Toast.makeText(this,"Successfully Created",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }


           }

    }
}