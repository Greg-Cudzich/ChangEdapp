package com.example.administrator.changedapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern



    class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }





    fun SecondScreen(view: View){

        val screenswitch =  Intent(this, WelcomeScreen::class.java)
        var editText  = findViewById(R.id.email)as EditText
        var x = editText.getText().toString()

        var pass = findViewById(R.id.password1) as TextInputEditText
        var y = pass.getText().toString()
        var pass2 =  findViewById(R.id.password2) as TextInputEditText
        var z = pass2.getText().toString()
        if(isEmailValid(x) == true && isValidPassword(y,z) == true){
            startActivity(screenswitch)
        }
        else {
            Toast.makeText(this, "Invalid Email or Password, Try Again", Toast.LENGTH_SHORT).show()
        }
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(pass1: String, pass2: String): Boolean {
        var valid = false
        if (pass1.length == 0 && pass2.length == 0) {
            return valid
        }
        if (pass1.compareTo(pass2) == 0) {
            valid = true
        }
        return valid
    }
}
