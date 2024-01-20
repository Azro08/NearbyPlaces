package com.example.domain.domain.usecase

import com.example.domain.domain.model.Place
import com.example.domain.domain.repository.PlacesApiRepository
import com.example.domain.domain.repository.PlacesDbRepository
import java.net.UnknownHostException

class PlacesUseCase(
    private val apiRepository: PlacesApiRepository,
    private val dbRepository: PlacesDbRepository
) {
    suspend operator fun invoke(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ): List<Place>? {
        return try {
            val apiData = apiRepository.getNearbyPlaces(coordinates, radius, limit, sessionToken)

            val dbData = dbRepository.getPlaces()

            if (apiData != dbData) {
                dbRepository.deleteAll()
                dbRepository.insertAllPlaces(apiData)
            }
            dbRepository.getPlaces()
        } catch (e: UnknownHostException) {
            val dbData = dbRepository.getPlaces()
            return dbData.ifEmpty { null }
        }

    }

}