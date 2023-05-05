package com.example.carrerquestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Front : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)

        val frontsignInBtn : Button = findViewById(R.id.frontsignInBtn)
        val frontsignUpBtn : Button = findViewById(R.id.frontsignUpBtn)

        frontsignInBtn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        frontsignUpBtn.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }
}