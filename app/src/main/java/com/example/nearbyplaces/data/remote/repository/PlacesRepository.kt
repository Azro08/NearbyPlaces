package com.example.nearbyplaces.data.remote.repository

import com.example.nearbyplaces.data.remote.api.PlacesApiService
import javax.inject.Inject

class PlacesRepository
@Inject constructor(private val api: PlacesApiService) {
    suspend fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
    ) = api.getNearbyPlaces(
        coordinates = coordinates,
        radius = radius,
        limit = limit,
    )
}
