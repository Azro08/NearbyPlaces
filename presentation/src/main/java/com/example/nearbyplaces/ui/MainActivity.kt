package com.example.nearbyplaces.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.util.Screen
import com.example.nearbyplaces.ui.place_details.PlaceDetailsScreen
import com.example.nearbyplaces.ui.places_list.PlacesListScreen
import com.example.nearbyplaces.ui.theme.NearbyPlacesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NearbyPlacesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.PlacesListScreen.route
                ) {
                    composable(
                        route = Screen.PlacesListScreen.route
                    ) {
                        PlacesListScreen(navController)
                    }
                    composable(
                        route = Screen.PlaceDetailScreen.route + "/{placeId}"
                    ) {
                        PlaceDetailsScreen()
                    }
                }
            }
        }
    }
}