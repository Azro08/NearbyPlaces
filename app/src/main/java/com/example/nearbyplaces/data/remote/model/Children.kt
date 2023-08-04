package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("name")
    val name: String
)