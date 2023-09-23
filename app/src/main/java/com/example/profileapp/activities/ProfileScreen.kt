package com.example.profileapp.activities

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.profileapp.databinding.ActivityProfileScreenBinding
import com.example.profileapp.model.PostDataRequest
import com.example.profileapp.model.ProfileResponse
import com.example.profileapp.service.ApiClient
import com.example.profileapp.service.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream


class ProfileScreen : AppCompatActivity() {

    private var profileBinding: ActivityProfileScreenBinding? = null
    private val apiService: ApiService = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileBinding = ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(profileBinding!!.root)

        profileBinding?.submitData?.setOnClickListener {
            // Get text input values
            val name = profileBinding?.nameEt?.text.toString()
            val email = profileBinding?.emailEt?.text.toString()
            val mobile = profileBinding?.mobileEt?.text.toString()
            val address = profileBinding?.addressEt?.text.toString()
            val pincode = profileBinding?.codeEt?.text.toString()

            val requestData = PostDataRequest(name, email, mobile, address, pincode)
            // Make the API call
            val call: Call<ProfileResponse> = apiService.postData(requestData)
            call.enqueue(object : Callback<ProfileResponse>{
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    profileResponse: Response<ProfileResponse>
                ) {
                    if (profileResponse.isSuccessful) {
                        val yourResponse = profileResponse.body()
                        Log.d("API Response", yourResponse?.message ?: "No message received")
                        val alertDialogBuilder = AlertDialog.Builder(this@ProfileScreen)
                        alertDialogBuilder.setTitle("Success") // Change to "Success"
                        alertDialogBuilder.setMessage("Data Submitted Successfully!")

                        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                            finish()
                            dialog.dismiss()
                        }
                        val alertDialog = alertDialogBuilder.create()
                        alertDialog.show() // Show the dialog
                    } else {
                        // Handle API error (non-successful response)
                        Log.e(
                            "API Error",
                            "Error: ${profileResponse.code()} - ${profileResponse.message()}"
                        )
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                }
            })
        }
    }

}