package com.example.data.util

sealed class Screen(val route: String) {
    object PlacesListScreen: Screen("places_list_screen")
    object PlaceDetailScreen: Screen("place_detail_screen")
}