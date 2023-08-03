package com.example.nearbyplaces.data.remote.model


import com.example.nearbyplaces.data.remote.model.Children
import com.example.nearbyplaces.data.remote.model.Parent
import com.google.gson.annotations.SerializedName

data class RelatedPlaces(
    @SerializedName("children")
    val children: List<Children>?,
    @SerializedName("parent")
    val parent: Parent?
)