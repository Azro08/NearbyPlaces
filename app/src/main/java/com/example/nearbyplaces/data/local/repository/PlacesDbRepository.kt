package com.example.nearbyplaces.data.local.repository

import com.example.nearbyplaces.data.local.dao.PlacesDao
import com.example.nearbyplaces.data.local.entity.PlaceEntity
import javax.inject.Inject

class PlacesDbRepository
@Inject constructor(private val dao: PlacesDao) {

    suspend fun insertAllPlaces(placesList: List<PlaceEntity>) = dao.insertAllPlaced(placesList)

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun getPlaces(): List<PlaceEntity> = dao.getPlaces()


    suspend fun getPlaceByFsqId(fsqId: String): PlaceEntity? = dao.getPlaceByFsqId(fsqId)
}