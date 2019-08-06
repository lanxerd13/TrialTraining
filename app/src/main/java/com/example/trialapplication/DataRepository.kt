package com.example.trialapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    fun create(): PostServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .baseUrl("http://122.248.38.212:8882/api/get-province-list/")
            .build()
        return retrofit.create(PostServices::class.java)
    }
}