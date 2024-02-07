package com.example.nearbyplaces.helper

import com.example.domain.model.Category
import com.example.domain.model.Icon
import com.example.domain.model.Location
import com.example.domain.model.Place
import com.example.nearbyplaces.model.CategoryModel
import com.example.nearbyplaces.model.IconModel
import com.example.nearbyplaces.model.LocationModel
import com.example.nearbyplaces.model.PlaceModel


fun Place.toPlaceModel(): PlaceModel =
    PlaceModel(
        categories = categories.map { it.toCategoryModel() },
        distance = distance,
        link = link,
        location = location.toLocationModel(),
        name = name,
        timezone = timezone,
        fsqId = fsqId
    )

fun Category.toCategoryModel(): CategoryModel =
    CategoryModel(
        icon = icon.toIconModel(),
        name = name
    )

fun Icon.toIconModel(): IconModel =
    IconModel(
        prefix = prefix,
        suffix = suffix
    )

fun Location.toLocationModel(): LocationModel =
    LocationModel(
        address = address,
        region = region
    )
