package com.example.carrerquestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityAdddataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddData : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var binding : ActivityAdddataBinding
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdddataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val CancelBtn : Button = findViewById(R.id.CancelBtn)

        CancelBtn.setOnClickListener{
            val intent = Intent(this,Note::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {

            val jobName = binding.firstName.text.toString()
            val aboutJob = binding.lastName.text.toString()
            val date = binding.age.text.toString()
            val userName = binding.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("User")
            val User = User(jobName,aboutJob,date,userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.age.text.clear()
                binding.userName.text.clear()

                Toast.makeText(this,"Successfully Created",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }


        }

    }
}