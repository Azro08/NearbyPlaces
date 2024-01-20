package com.example.data.remote.api

import com.example.data.remote.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlacesApiService {
    @GET("v3/places/search")
    @Headers(
        "accept: application/json",
        "Authorization: fsq3MVO6HpccxvsMqOVRtpwEjI28YIM1rteqNl6TjCRMRKU="
    )
    suspend fun getNearbyPlaces(
        @Query("ll") coordinates: String,
        @Query("radius") radius: Int,
        @Query("categories") categories: String = "13000",
        @Query("limit") limit: Int,
        @Query("session_token") sessionToken: String,
        @Query("accept") accept: String = "application/json",
    ): PlaceResponse
}
