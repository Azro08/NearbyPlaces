package com.example.data.remote.model


import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("address")
    val address: String?,
    @SerializedName("region")
    val region: String
)