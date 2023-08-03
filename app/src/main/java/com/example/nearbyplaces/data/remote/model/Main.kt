package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)