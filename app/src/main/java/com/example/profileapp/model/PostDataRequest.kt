package com.example.profileapp.model

import com.google.gson.annotations.SerializedName

data class PostDataRequest(
 @SerializedName("name")
 val name: String,
 @SerializedName("email")
 val email: String,
 @SerializedName("mobile")
 val mobile: String,
 @SerializedName("address")
 val address: String,
 @SerializedName("pincode")
 val pincode: String
)