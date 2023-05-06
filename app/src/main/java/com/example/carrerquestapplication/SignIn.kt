package com.example.carrerquestapplication

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        val signInEmail : EditText = findViewById(R.id.signInEmail)
        val signInPassword : EditText = findViewById(R.id.signInPassword)
        val signInPasswordLayout : TextInputLayout = findViewById(R.id.signUInPasswordLayout)
        val signInBtn : Button = findViewById(R.id.signInBtn)
        val signInProgressBar : ProgressBar = findViewById(R.id.signInProgressBar)


        val signUpText : TextView = findViewById(R.id.signUpText)
        val forgot_password : TextView = findViewById(R.id.forgot_password)

        signUpText.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        forgot_password.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_forgot,null)
            val userEmail = view.findViewById<EditText>(R.id.editBox)

            builder.setView(view)
            val dialog = builder.create()

            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
                compareEmail(userEmail)
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if(dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }


        signInBtn.setOnClickListener {
            signInProgressBar.visibility = View.VISIBLE
            signInPasswordLayout.isPasswordVisibilityToggleEnabled = true

            val email = signInEmail.text.toString()
            val password = signInPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                if(email.isEmpty()){
                    signInEmail.error = "Enter Your Email address"
                }
                if(password.isEmpty()){
                    signInPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    signInPassword.error = "Enter your Password"
                }
                signInProgressBar.visibility = View.GONE
                Toast.makeText(this,"Enter valid details", Toast.LENGTH_SHORT).show()
            }else if(!email.matches(emailpattern.toRegex())){
                signInProgressBar.visibility = View.GONE
                signInEmail.error = "Enter a valid Email address"
                Toast.makeText(this,"Enter a valid email address",Toast.LENGTH_SHORT).show()
            }else if(password.length < 8){
                signInPasswordLayout.isPasswordVisibilityToggleEnabled = false
                signInProgressBar.visibility = View.GONE
                signInPassword.error = "Enter password more than 8 characters"
                Toast.makeText(this,"Enter password more than 8 characters",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this,Note::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Something went wrong, try again",Toast.LENGTH_SHORT).show()
                        signInProgressBar.visibility = View.GONE
                    }
                }
            }
        }

    }
    //Outside onCreate
    private fun compareEmail(email:EditText){
        if(email.text.toString().isEmpty()){
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Check your email",Toast.LENGTH_SHORT).show()
            }
        }
    }
}