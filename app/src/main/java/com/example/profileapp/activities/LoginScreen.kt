package com.example.profileapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.profileapp.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {

    private var loginBinding: ActivityLoginScreenBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(loginBinding!!.root)

        val intent : Intent
        intent = getIntent()
        var userOtp = intent.getStringExtra("otp")

                loginBinding?.submitButton?.setOnClickListener(View.OnClickListener {

                    var enterredOtp = loginBinding?.pinview?.text.toString()

                        if (enterredOtp.equals(userOtp)) {
                            var intent = Intent(this@LoginScreen, ProfileScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this@LoginScreen,"Wrong Otp!!",Toast.LENGTH_SHORT)
                        }

                })

        loginBinding?.backArr?.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@LoginScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        loginBinding?.resend?.setOnClickListener(View.OnClickListener {
            var intent = Intent(this@LoginScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

    }
}