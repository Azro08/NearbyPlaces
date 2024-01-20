package com.example.domain.domain.model

data class Place(
    val fsqId: String,
    val categories: List<Category>,
    val distance: Int,
    val link: String,
    val location: Location,
    val name: String,
    val timezone: String
)
