package com.example.nearbyplaces.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nearbyplaces.domain.model.Category
import com.example.nearbyplaces.domain.model.Location

data class Place(
    val fsqId: String,
    val categories: List<Category>,
    val distance: Int,
    val link: String,
    val location: Location,
    val name: String,
    val timezone: String
)
