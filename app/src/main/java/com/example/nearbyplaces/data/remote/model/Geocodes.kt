package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("main")
    val main: Main
)