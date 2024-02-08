package com.example.nearbyplaces.ui.places_list

import androidx.activity.compose.setContent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.SmallTest
import com.example.data.util.Screen
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.ui.MainActivity
import com.example.nearbyplaces.ui.place_details.PlaceDetailsScreen
import com.example.nearbyplaces.ui.theme.NearbyPlacesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@HiltAndroidTest
class PlacesListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activityRule.scenario.onActivity {
            it.setContent {
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

//        composeRule.activity.setContent {
//
//        }
    }

    @Test
    fun placesList_isVisible() {
        composeRule.onNodeWithTag(Constants.PLACES_TEST_TAG).assertExists()
    }

}