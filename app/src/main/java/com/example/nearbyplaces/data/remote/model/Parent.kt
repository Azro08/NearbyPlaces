package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("name")
    val name: String
)