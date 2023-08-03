package com.example.nearbyplaces.data.remote.model


import com.example.nearbyplaces.data.remote.model.Category
import com.example.nearbyplaces.data.remote.model.Geocodes
import com.example.nearbyplaces.data.remote.model.Location
import com.example.nearbyplaces.data.remote.model.RelatedPlaces
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("chains")
    val chains: List<Any>,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("geocodes")
    val geocodes: Geocodes,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces?,
    @SerializedName("timezone")
    val timezone: String
)