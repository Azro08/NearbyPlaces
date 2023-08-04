package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("results")
    val results: List<Results>
)