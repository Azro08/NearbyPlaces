package com.example.data.remote.model


import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("results")
    val place: List<PlaceDto>
)
