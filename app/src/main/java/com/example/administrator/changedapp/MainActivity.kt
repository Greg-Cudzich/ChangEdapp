package com.example.administrator.changedapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.design.widget.TextInputEditText
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import android.app.PendingIntent
import com.google.android.gms.common.api.GoogleApiClient
import android.widget.TextView
import com.google.android.gms.common.api.Scope
import com.sun.org.apache.xpath.internal.operations.Plus










     class MainActivity : AppCompatActivity(), ConnectionCallbacks, OnConnectionFailedListener, View.OnClickListener{

         private val SIGNED_IN = 0
         private val STATE_SIGNING_IN = 1
         private val STATE_IN_PROGRESS = 2
         private val RC_SIGN_IN = 0
         private val mGoogleApiClient: GoogleApiClient? = null
         private val mSignInProgress: Int = 0
         private val mSignInIntent: PendingIntent? = null

         private val mSignInButton: SignInButton? = null
         private val mStatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mSignInButton = findViewById(R.id.google_button) as SignInButton

        mSignInButton.setOnClickListener(this)
        mGoogleApiClient = buildGoogleApiClient()

    }

         private fun buildGoogleApiClient(): GoogleApiClient {
             return GoogleApiClient.Builder(this)
                     .addConnectionCallbacks(this)
                     .addOnConnectionFailedListener(this)
                     .addApi(Plus.API, Plus.PlusOptions.builder().build())
                     .addScope(Scope("email"))
                     .build()
         }

         override fun onStart() {
             super.onStart()
             if (mGoogleApiClient != null) {
                 mGoogleApiClient.connect()
             }
         }

         override fun onStop() {
             super.onStop()
             if (mGoogleApiClient != null) {
                 mGoogleApiClient.disconnect()
             }
         }

         override fun onConnectionSuspended(cause: Int) {
             mGoogleApiClient.connect()
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
