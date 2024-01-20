package com.example.data.local.repository

import com.example.data.local.dao.PlacesDao
import com.example.data.mapper.toPlace
import com.example.data.mapper.toPlaceEntity
import com.example.domain.domain.model.Place
import com.example.domain.domain.repository.PlacesDbRepository
import javax.inject.Inject

class PlacesDbRepositoryImpl
@Inject constructor(private val dao: PlacesDao) : PlacesDbRepository {

    override suspend fun insertAllPlaces(placesList: List<Place>) =
        dao.insertAllPlaced(placesList.map { it.toPlaceEntity() })

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun getPlaces(): List<Place> = dao.getPlaces().map { it.toPlace() }


    override suspend fun getPlaceByFsqId(fsqId: String): Place? =
        dao.getPlaceByFsqId(fsqId)?.toPlace()
}