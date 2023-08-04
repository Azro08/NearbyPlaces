package com.example.nearbyplaces.data.local.room

import androidx.room.TypeConverter
import com.example.nearbyplaces.data.local.entity.CategoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoriesEntityTypeConverter {

    @TypeConverter
    fun fromList(categoryList: List<CategoryEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.toJson(categoryList, type)
    }

    @TypeConverter
    fun toList(categoryListString: String): List<CategoryEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.fromJson(categoryListString, type)
    }
}