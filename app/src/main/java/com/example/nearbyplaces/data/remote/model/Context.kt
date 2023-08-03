package com.example.nearbyplaces.data.remote.model


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("geo_bounds")
    val geoBounds: GeoBounds
)