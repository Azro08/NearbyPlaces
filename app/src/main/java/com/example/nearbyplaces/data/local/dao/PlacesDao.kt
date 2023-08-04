package com.example.nearbyplaces.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nearbyplaces.data.local.entity.PlaceEntity

@Dao
interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlaced(placesList: List<PlaceEntity>)

    @Query("DELETE FROM places_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM places_table")
    suspend fun getPlaces(): List<PlaceEntity>

    @Query("SELECT * FROM places_table WHERE fsqId = :fsqId")
    suspend fun getPlaceByFsqId(fsqId: String): PlaceEntity?

}
