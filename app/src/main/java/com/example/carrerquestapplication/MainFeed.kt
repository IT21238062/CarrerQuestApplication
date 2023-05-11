package com.example.carrerquestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_feed)

        val AddNoteBtn : Button = findViewById(R.id.AddNoteBtn)
        val ReadNoteBtn : Button = findViewById(R.id.ReadNoteBtn)
        val UpdateNoteBtn : Button = findViewById(R.id.UpdateNoteBtn)
        val DeleteNoteBtn : Button = findViewById(R.id.DeleteNoteBtn)

        AddNoteBtn.setOnClickListener{
            val intent = Intent(this,AddFeedback::class.java)
            startActivity(intent)
        }

        ReadNoteBtn.setOnClickListener{
            val intent = Intent(this,ReadFeedback::class.java)
            startActivity(intent)
        }

        UpdateNoteBtn.setOnClickListener{
            val intent = Intent(this,UpdateFeedBack::class.java)
            startActivity(intent)
        }

        DeleteNoteBtn.setOnClickListener{
            val intent = Intent(this,DeleteFeedback::class.java)
            startActivity(intent)
        }
    }
}