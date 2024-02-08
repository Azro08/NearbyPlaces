package com.example.nearbyplaces.ui.places_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.data.util.Screen
import com.example.nearbyplaces.R
import com.example.nearbyplaces.helper.AuthManager
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.helper.ScreenState
import com.example.nearbyplaces.model.PlaceModel

@Composable
fun PlacesListScreen(
    navController: NavController,
    viewModel: PlacesViewModel = hiltViewModel()
) {
    var placesList by remember { mutableStateOf<List<PlaceModel>>(emptyList()) }
    var errorMsg by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize().testTag(Constants.PLACES_TEST_TAG)) {
        if (placesList.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize() ) {
                items(items = placesList, key = { place -> place.fsqId }) { place ->
                    PlaceItem(place = place) {
                        navController.navigate(Screen.PlaceDetailScreen.route + "/${place.fsqId}")
                    }
                }
            }
        } else {
            Text(text = errorMsg, fontSize = 24.sp, modifier = Modifier.align(Alignment.Center))
        }
    }

    LaunchedEffect(Unit) {
        val accessToken = AuthManager.getAuthenticationToken(context)
        Log.d("access_token", accessToken)
        val ll = "53.9057644,27.558230"
        val limit = 50
        val radius = 4000
        viewModel.getNearbyPlaces(
            coordinates = ll,
            limit = limit,
            radius = radius,
            sessionToken = accessToken
        )
        viewModel.responsePlaces.collect { responseState ->
            when (responseState) {
                is ScreenState.Loading -> {}

                is ScreenState.Success -> {
                    placesList = responseState.data!!
                    Log.d("PlacesList", responseState.data.size.toString())
                }

                is ScreenState.Error -> {
                    errorMsg = responseState.message!!
                }
            }
        }
    }
}

@Composable
fun PlaceItem(
    place: PlaceModel,
    onClickListener: (PlaceModel) -> Unit
) {
    val iconUrl = place.categories[0].icon.prefix + place.categories[0].icon.suffix
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
            .background(colorResource(id = R.color.light_gray))
            .clickable {
                onClickListener(place)
            }
    ) {
        AsyncImage(
            model = iconUrl,
            placeholder = painterResource(id = R.drawable.place_img),
            error = painterResource(id = R.drawable.place_img),
            contentDescription = "Place Icon",
            modifier = Modifier.size(90.dp)
        )

        Column(modifier = Modifier.height(100.dp)) {
            Text(
                text = place.name,
                fontSize = 20.sp,
                color = Color.Blue,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = place.categories[0].name,
                fontSize = 18.sp,
                color = Color.DarkGray
            )

            place.location.address?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}