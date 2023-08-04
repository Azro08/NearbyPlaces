package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("icon")
    val icon: IconDto,
    @SerializedName("name")
    val name: String
)