package com.example.nearbyplaces.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nearbyplaces.data.local.dao.PlacesDao
import com.example.nearbyplaces.data.local.entity.PlaceEntity

@TypeConverters(CategoriesEntityTypeConverter::class, LocationEntityTypeConverter::class)
@Database(
    entities = [PlaceEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PlacesDataBase : RoomDatabase() {
    abstract fun getPlacesDao() : PlacesDao

    companion object {
        @Volatile
        private var database: PlacesDataBase? = null

        const val dbName = "placesDb"

        @Synchronized
        fun getInstance(context: Context): PlacesDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(context, PlacesDataBase::class.java, dbName).build()
                database as PlacesDataBase
            } else {
                database as PlacesDataBase
            }
        }
    }

}