package com.example.domain.repository

import com.example.domain.model.Place

interface PlacesApiRepository {
    suspend fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ): List<Place>
}
