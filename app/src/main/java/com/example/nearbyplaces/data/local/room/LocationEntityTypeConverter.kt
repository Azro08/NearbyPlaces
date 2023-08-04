package com.example.nearbyplaces.data.local.room

import androidx.room.TypeConverter
import com.example.nearbyplaces.data.local.entity.LocationEntity
import com.google.gson.Gson

class LocationEntityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): LocationEntity {
        return gson.fromJson(value, LocationEntity::class.java)
    }

    @TypeConverter
    fun toString(location: LocationEntity): String {
        return gson.toJson(location)
    }
}
