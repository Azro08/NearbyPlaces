package com.example.data.remote.repository

import com.example.data.remote.api.PlacesApiService
import com.example.data.util.toPlace
import com.example.domain.domain.repository.PlacesApiRepository
import javax.inject.Inject

class PlacesApiRepositoryImpl
@Inject constructor(private val api: PlacesApiService) : PlacesApiRepository {
    override suspend fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) = api.getNearbyPlaces(
        coordinates = coordinates,
        radius = radius,
        limit = limit,
        sessionToken = sessionToken
    ).place.map { it.toPlace() }
}
