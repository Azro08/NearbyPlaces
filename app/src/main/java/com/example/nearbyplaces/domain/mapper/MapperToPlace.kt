package com.example.nearbyplaces.domain.mapper

import com.example.nearbyplaces.data.local.entity.CategoryEntity
import com.example.nearbyplaces.data.local.entity.IconEntity
import com.example.nearbyplaces.data.local.entity.LocationEntity
import com.example.nearbyplaces.data.local.entity.PlaceEntity
import com.example.nearbyplaces.domain.model.Category
import com.example.nearbyplaces.domain.model.Icon
import com.example.nearbyplaces.domain.model.Location
import com.example.nearbyplaces.domain.model.Place

fun PlaceEntity.toPlace(): Place =
    Place(
        categories = categories.map { it.toCategory() },
        distance = distance,
        link = link,
        location = location.toLocation(),
        name = name,
        timezone = timezone,
        fsqId = fsqId
    )

fun CategoryEntity.toCategory(): Category =
    Category(
        icon = icon.toIcon(),
        name = name
    )

fun IconEntity.toIcon(): Icon =
    Icon(
        prefix = prefix,
        suffix = suffix
    )

fun LocationEntity.toLocation(): Location =
    Location(
        address = address,
        region = region
    )