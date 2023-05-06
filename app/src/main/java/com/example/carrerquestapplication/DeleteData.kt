package com.example.carrerquestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carrerquestapplication.databinding.ActivityDeleteDataBinding
import com.example.carrerquestapplication.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteData : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deletedataBtn.setOnClickListener {

            val userName = binding.etusername.text.toString()
            if(userName.isNotEmpty())
                deleteData(userName)
            else
                Toast.makeText(this,"Please Enter the username", Toast.LENGTH_SHORT).show()

        }
    }

    private fun deleteData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(userName).removeValue().addOnSuccessListener {

            binding.etusername.text.clear()
            Toast.makeText(this,"Successfully Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }
}