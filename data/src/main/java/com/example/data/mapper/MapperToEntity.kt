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

fun PlaceDto.toPlaceEntity(): PlaceEntity =
    PlaceEntity(
        categories = categories.map { it.toCategoryEntity() },
        distance = distance,
        link = link,
        location = location.toLocationEntity(),
        name = name,
        timezone = timezone,
        fsqId = fsqId
    )

fun CategoryDto.toCategoryEntity(): CategoryEntity =
    CategoryEntity(
        icon = icon.toIconEntity(),
        name = name
    )

fun IconDto.toIconEntity(): IconEntity =
    IconEntity(
        prefix = prefix,
        suffix = suffix
    )

fun LocationDto.toLocationEntity(): LocationEntity =
    LocationEntity(
        address = address,
        region = region
    )

fun Place.toPlaceEntity(): PlaceEntity =
    PlaceEntity(
        categories = categories.map { it.toCategoryEntity() },
        distance = distance,
        link = link,
        location = location.toLocationEntity(),
        name = name,
        timezone = timezone,
        fsqId = fsqId
    )

fun Category.toCategoryEntity(): CategoryEntity =
    CategoryEntity(
        icon = icon.toIconEntity(),
        name = name
    )

fun Icon.toIconEntity(): IconEntity =
    IconEntity(
        prefix = prefix,
        suffix = suffix
    )

fun Location.toLocationEntity(): LocationEntity =
    LocationEntity(
        address = address,
        region = region
    )