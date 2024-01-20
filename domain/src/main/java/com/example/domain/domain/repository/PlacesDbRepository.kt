package com.example.domain.domain.repository

import com.example.domain.domain.model.Place

interface PlacesDbRepository {

    suspend fun insertAllPlaces(placesList: List<Place>)

    suspend fun deleteAll()

    suspend fun getPlaces(): List<Place>


    suspend fun getPlaceByFsqId(fsqId: String): Place?
}