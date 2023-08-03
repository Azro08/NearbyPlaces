package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("icon")
    val icon: Icon,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)