package com.example.nearbyplaces.data.remote.model


import com.example.nearbyplaces.data.remote.model.Circle
import com.google.gson.annotations.SerializedName

data class GeoBounds(
    @SerializedName("circle")
    val circle: Circle
)