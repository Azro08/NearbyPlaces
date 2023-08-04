package com.example.nearbyplaces.domain.usecase

import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.data.local.repository.PlacesDbRepository
import com.example.nearbyplaces.data.remote.repository.PlacesApiRepository
import com.example.nearbyplaces.domain.mapper.toPlace
import com.example.nearbyplaces.domain.mapper.toPlaceEntity
import java.net.UnknownHostException
import javax.inject.Inject

class PlacesUseCase @Inject constructor(
    private val apiRepository: PlacesApiRepository,
    private val dbRepository: PlacesDbRepository
) {

    suspend operator fun invoke(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) : ScreenState<List<Place>?> {

        return try {
            // Fetch data from API
            val apiData = apiRepository.getNearbyPlaces(coordinates, radius, limit, sessionToken)
                .body()?.place?.map { it.toPlaceEntity() }

            // Fetch existing data from the database
            val dbData = dbRepository.getPlaces()

            // Compare the new API data with the existing data in the database
            if (apiData != dbData) {
                // Data is different, update it in the database
                dbRepository.deleteAll()
                dbRepository.insertAllPlaces(apiData!!)
            }
            ScreenState.Success(dbData.map { it.toPlace() })
        } catch (e: UnknownHostException){
            val dbData = dbRepository.getPlaces()
            return if (dbData.isEmpty()) ScreenState.Error(e.message.toString())
            else ScreenState.Success(dbData.map { it.toPlace() })
        }

    }

}