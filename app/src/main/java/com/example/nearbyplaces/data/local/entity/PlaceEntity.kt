package com.example.nearbyplaces.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.nearbyplaces.data.local.room.CategoriesEntityTypeConverter
import com.example.nearbyplaces.data.local.room.LocationEntityTypeConverter

@Entity(tableName = "places_table")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val fsqId: String,
    @TypeConverters(CategoriesEntityTypeConverter::class) val categories: List<CategoryEntity>,
    val distance: Int,
    val link: String,
    @TypeConverters(LocationEntityTypeConverter::class) val location: LocationEntity,
    val name: String,
    val timezone: String
)
