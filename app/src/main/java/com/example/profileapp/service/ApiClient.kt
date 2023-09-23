package com.example.profileapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        //private const val BASE_URL = "http://dev.cbticket.projects-codingbrains.com/api/"
        private const val BASE_URL = "https://whooradio.com/api/master/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}