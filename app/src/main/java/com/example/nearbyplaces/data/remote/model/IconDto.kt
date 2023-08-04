package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class IconDto(
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("suffix")
    val suffix: String
)