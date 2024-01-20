package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.PlacesDao
import com.example.data.local.entity.PlaceEntity

@TypeConverters(CategoriesEntityTypeConverter::class, LocationEntityTypeConverter::class)
@Database(
    entities = [PlaceEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PlacesDataBase : RoomDatabase() {
    abstract fun getPlacesDao(): PlacesDao

    companion object {

        const val dbName = "placesDb"

    }

}