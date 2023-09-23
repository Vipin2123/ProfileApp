package com.example.profileapp.service


import com.example.profileapp.model.PostDataRequest
import com.example.profileapp.model.ProfileResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
       /* @Multipart*/
       /* @POST("user-profile")
        fun postData(
               // @Part image: MultipartBody.Part,
                @Part("name") name: RequestBody,
                @Part("email") email: RequestBody,
                @Part("mobile") mobile: RequestBody,
                @Part("address") address: RequestBody,
                @Part("pincode") pincode: RequestBody
        ): Call<ProfileResponse>*/


        @POST("home-data?user_id")
        fun postData(@Body request: PostDataRequest): Call<ProfileResponse>

}