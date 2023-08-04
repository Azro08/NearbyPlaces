package com.example.nearbyplaces.domain.usecase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nearbyplaces.domain.model.Category
import com.example.nearbyplaces.domain.model.Location

data class Place(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val fsqId: String,
    val categories: List<Category>,
    val distance: Int,
    val link: String,
    val location: Location,
    val name: String,
    val timezone: String
)
