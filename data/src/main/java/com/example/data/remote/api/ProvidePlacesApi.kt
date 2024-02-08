package com.example.data.remote.api

import com.example.data.di.NetworkModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ProvidePlacesApi {

    fun providePlacesApi(): PlacesApiService =
        Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlacesApiService::class.java)

}