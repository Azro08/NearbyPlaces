package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaceDto(
    @SerializedName("categories") val categories: List<CategoryDto>,
    @SerializedName("distance") val distance: Int,
    @SerializedName("link") val link: String,
    @SerializedName("location") val location: LocationDto,
    @SerializedName("name") val name: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("fsq_id") val fsqId: String
)
