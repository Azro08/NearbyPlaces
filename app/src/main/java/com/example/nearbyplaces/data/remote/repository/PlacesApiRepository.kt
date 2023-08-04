package com.example.nearbyplaces.data.remote.repository

import com.example.nearbyplaces.data.remote.api.PlacesApiService
import javax.inject.Inject

class PlacesApiRepository
@Inject constructor(private val api: PlacesApiService) {
    suspend fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) = api.getNearbyPlaces(
        coordinates = coordinates,
        radius = radius,
        limit = limit,
        sessionToken = sessionToken
    )
}
