package com.example.nearbyplaces.domain.mapper

import com.example.nearbyplaces.data.local.entity.CategoryEntity
import com.example.nearbyplaces.data.local.entity.IconEntity
import com.example.nearbyplaces.data.local.entity.LocationEntity
import com.example.nearbyplaces.data.local.entity.PlaceEntity
import com.example.nearbyplaces.data.remote.model.CategoryDto
import com.example.nearbyplaces.data.remote.model.IconDto
import com.example.nearbyplaces.data.remote.model.LocationDto
import com.example.nearbyplaces.data.remote.model.PlaceDto

fun PlaceDto.toPlaceEntity() : PlaceEntity =
    PlaceEntity(
        categories = categories.map { it.toCategoryEntity() },
        distance = distance,
        link = link,
        location = location.toLocationEntity(),
        name = name,
        timezone = timezone,
        fsqId = fsqId
    )

fun CategoryDto.toCategoryEntity() : CategoryEntity =
    CategoryEntity(
     icon = icon.toIconEntity(),
     name = name
    )

fun IconDto.toIconEntity() : IconEntity =
    IconEntity(
        prefix = prefix,
        suffix = suffix
    )

fun LocationDto.toLocationEntity() : LocationEntity =
    LocationEntity(
        address = address,
        region = region
    )