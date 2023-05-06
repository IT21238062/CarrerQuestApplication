package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

    private lateinit var binding : ActivityReadDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val userName : String = binding.etusername.text.toString()
            if  (userName.isNotEmpty()){

                readData(userName)

            }else{

                Toast.makeText(this,"PLease enter the Username",Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun readData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(userName).get().addOnSuccessListener {

            if (it.exists()){

                val jobName = it.child("jobName").value
                val aboutJob = it.child("aboutJob").value
                val date = it.child("date").value
                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                binding.etusername.text.clear()
                binding.tvFirstName.text = jobName.toString()
                binding.tvLastName.text = aboutJob.toString()
                binding.tvAge.text = date.toString()

            }else{

                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


        }



    }
}