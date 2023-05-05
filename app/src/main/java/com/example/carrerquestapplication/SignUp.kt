package com.example.carrerquestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val signUpName : EditText = findViewById(R.id.signUpName)
        val signUpEmail : EditText = findViewById(R.id.signUpEmail)
        val signUpPhone : EditText = findViewById(R.id.signUpPhone)
        val signUpPassword : EditText = findViewById(R.id.signUpPassword)
        val signUpCPassword : EditText = findViewById(R.id.signUpCPassword)
        val signUpPasswordLayout : TextInputLayout = findViewById(R.id.signUpPasswordLayout)
        val signUpCPasswordLayout : TextInputLayout = findViewById(R.id.signUpCPasswordLayout)
        val signUpBtn : Button = findViewById(R.id.signUpBtn)
        val signUpProgressbar : ProgressBar = findViewById(R.id.signUpProgressBar)

        val signInText: TextView = findViewById(R.id.signInText)

        signInText.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener{
            val name = signUpName.text.toString()
            val email = signUpEmail.text.toString()
            val phone = signUpPhone.text.toString()
            val password = signUpPassword.text.toString()
            val cPassword = signUpCPassword.text.toString()

            signUpProgressbar.visibility = View.VISIBLE
            signUpPasswordLayout.isPasswordVisibilityToggleEnabled = true
            signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = true

            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || cPassword.isEmpty()){
                if(name.isEmpty()){
                    signUpName.error = "Enter Your Name"
                }
                if(email.isEmpty()){
                    signUpEmail.error = "Enter Your Email address"
                }
                if(phone.isEmpty()){
                    signUpPhone.error = "Enter Your Phone Number"
                }
                if(password.isEmpty()){
                    signUpPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    signUpPassword.error = "Enter a Password"
                }
                if(cPassword.isEmpty()){
                    signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    signUpCPassword.error = "ReEnter the Password"
                }
                Toast.makeText(this,"Enter valid details",Toast.LENGTH_SHORT).show()
                signUpProgressbar.visibility = View.GONE
            }else if(!email.matches(emailpattern.toRegex())){
                signUpProgressbar.visibility = View.GONE
                signUpEmail.error = "Enter a valid Email address"
                Toast.makeText(this,"Enter a valid email address",Toast.LENGTH_SHORT).show()
            }else if(phone.length != 10){
                signUpProgressbar.visibility = View.GONE
                signUpPhone.error = "Enter a valid Phone number"
                Toast.makeText(this,"Enter a valid phone number",Toast.LENGTH_SHORT).show()
            }else if(password.length < 8){
                signUpPasswordLayout.isPasswordVisibilityToggleEnabled = false
                signUpProgressbar.visibility = View.GONE
                signUpPassword.error = "Enter password more than 8 characters"
                Toast.makeText(this,"Enter password more than 8 characters",Toast.LENGTH_SHORT).show()
            }else if(password != cPassword){
                signUpCPasswordLayout.isPasswordVisibilityToggleEnabled = false
                signUpProgressbar.visibility = View.GONE
                signUpCPassword.error = "Password not matched, try again"
                Toast.makeText(this,"Password not matched, try again",Toast.LENGTH_SHORT).show()
            }else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users : Users = Users(name, email, phone, auth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener{
                            if(it.isSuccessful){
                                val intent = Intent(this,SignIn::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this,"Something went wrong, try again",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else {
                        Toast.makeText(this,"Something went wrong, try again",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}