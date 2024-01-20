package com.example.data.mapper

import com.example.data.local.entity.CategoryEntity
import com.example.data.local.entity.IconEntity
import com.example.data.local.entity.LocationEntity
import com.example.data.local.entity.PlaceEntity
import com.example.data.remote.model.CategoryDto
import com.example.data.remote.model.IconDto
import com.example.data.remote.model.LocationDto
import com.example.data.remote.model.PlaceDto
import com.example.domain.domain.model.Category
import com.example.domain.domain.model.Icon
import com.example.domain.domain.model.Location
import com.example.domain.domain.model.Place

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

fun PlaceDto.toPlace(): Place = Place(
    categories = categories.map { it.toCategory() },
    distance = distance,
    link = link,
    location = location.toLocation(),
    name = name,
    timezone = timezone,
    fsqId = fsqId
)

fun CategoryDto.toCategory(): Category =
    Category(
        icon = icon.toIcon(),
        name = name
    )

fun IconDto.toIcon(): Icon =
    Icon(
        prefix = prefix,
        suffix = suffix
    )

fun LocationDto.toLocation(): Location =
    Location(
        address = address,
        region = region
    )
