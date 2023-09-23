package com.example.profileapp.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.profileapp.R
import com.example.profileapp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    var isFieldChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.sendOtpButton?.setOnClickListener( View.OnClickListener {

          isFieldChecked =  validateNumber()

            if (isFieldChecked){

                generateOtp()

            }

        })
    }

    private fun validateNumber(): Boolean {
        if (binding?.mobileEt!!.length() != 10)
        {
            binding?.mobileEt!!.error = "Mobile number is incorrect"
            return false
        }

        return true
    }

    private fun generateOtp() {
        val phoneNumber = binding?.mobileEt?.text.toString().trim()

        if (phoneNumber.length >= 4) {
            val firstTwoDigits = phoneNumber.substring(0, 2)
            val lastTwoDigits = phoneNumber.substring(phoneNumber.length - 2)

           val otp = generateOtp(firstTwoDigits, lastTwoDigits)
           // binding?.mobileEt?.setText(otp)

            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)

            val customLayout: View = layoutInflater.inflate(R.layout.custom_layout, null)
            builder.setView(customLayout)
            builder.setCancelable(false)
            var dialog = builder.create()
            dialog.show()

            var editText = customLayout.findViewById<EditText>(R.id.otp_txt)
            editText.setText(otp)

            var editTv = customLayout.findViewById<TextView>(R.id.editTv)


            editTv.setOnClickListener(View.OnClickListener {

                var intent = Intent(this@MainActivity, LoginScreen::class.java)
                intent.putExtra("otp" , otp)
                startActivity(intent)
                dialog.dismiss()
            })

        } else {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Error")
            alertDialogBuilder.setMessage("Mobile number is not correct")

            alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
                finish()
                dialog.dismiss()

            }
        }
    }

    private fun generateOtp(firstTwoDigits: String, lastTwoDigits: String): String {
        val randomOtp = Random.nextInt(0, 10000)
        return "$firstTwoDigits$lastTwoDigits"
    }

}