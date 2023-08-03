package com.example.nearbyplaces.data.remote.api

import com.example.nearbyplaces.data.remote.model.PlaceResponse
import com.example.nearbyplaces.helper.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlacesApiService {

    @GET("v3/places/search")
    @Headers("accept: application/json", "Authorization: ${Constants.AUTHORIZATION}")
    suspend fun getNearbyPlaces(
        @Query("ll") coordinates: String,
        @Query("radius") radius: Int,
        @Query("categories") categories: String = "13000",
        @Query("limit") limit: Int,
        @Query("accept") accept: String = "application/json",
    ): Response<PlaceResponse>
}
