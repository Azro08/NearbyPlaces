package com.example.nearbyplaces.model

data class PlaceModel(
    val fsqId: String,
    val categories: List<CategoryModel>,
    val distance: Int,
    val link: String,
    val location: LocationModel,
    val name: String,
    val timezone: String
)
