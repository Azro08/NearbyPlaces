package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.entity.PlaceEntity

@Dao
interface PlacesDao {

    @Upsert
    suspend fun insertAllPlaced(placesList: List<PlaceEntity>)

    @Query("DELETE FROM places_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM places_table")
    suspend fun getPlaces(): List<PlaceEntity>

    @Query("SELECT * FROM places_table WHERE fsqId = :fsqId")
    suspend fun getPlaceByFsqId(fsqId: String): PlaceEntity?

}
