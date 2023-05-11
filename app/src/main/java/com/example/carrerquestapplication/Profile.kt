package com.example.carrerquestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Profile : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val AddNoteBtn : Button = findViewById(R.id.AddNoteBtn)
        val ReadNoteBtn : Button = findViewById(R.id.ReadNoteBtn)
        val UpdateNoteBtn : Button = findViewById(R.id.UpdateNoteBtn)
        val DeleteNoteBtn : Button = findViewById(R.id.DeleteNoteBtn)

        AddNoteBtn.setOnClickListener{
            val intent = Intent(this,Note::class.java)
            startActivity(intent)
        }

        ReadNoteBtn.setOnClickListener{
            val intent = Intent(this,ReadData::class.java)
            startActivity(intent)
        }

        UpdateNoteBtn.setOnClickListener{
            val intent = Intent(this,UpdateData::class.java)
            startActivity(intent)
        }

        DeleteNoteBtn.setOnClickListener{
            val intent = Intent(this,AddApplication::class.java)
            startActivity(intent)
        }


    }




}