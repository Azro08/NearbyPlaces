package com.example.nearbyplaces.data.remote.model


import com.example.nearbyplaces.data.remote.model.Center
import com.google.gson.annotations.SerializedName

data class Circle(
    @SerializedName("center")
    val center: Center,
    @SerializedName("radius")
    val radius: Int
)