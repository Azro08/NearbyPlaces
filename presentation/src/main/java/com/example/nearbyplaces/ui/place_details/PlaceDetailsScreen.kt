package com.example.nearbyplaces.ui.place_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nearbyplaces.R
import com.example.nearbyplaces.helper.ScreenState
import com.example.nearbyplaces.model.PlaceModel

@Composable
fun PlaceDetailsScreen(
    viewModel: PlaceDetailsViewModel = hiltViewModel()
) {
    var place by remember { mutableStateOf<PlaceModel?>(null) }
    var errorMsg by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.responsePlace.collect { state ->
            when (state) {
                is ScreenState.Error -> errorMsg = state.message!!
                is ScreenState.Loading -> {}
                is ScreenState.Success -> place = state.data!!
                null -> {}
            }
        }
    }
    val iconUrl = place?.categories?.get(0)?.icon?.let { icon ->
        icon.prefix + icon.suffix
    } ?: ""

    Log.d("iconUrl", iconUrl)

    place?.let { placeModel ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (place != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    item {
                        ImageView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            iconUrl
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        DetailsItem(title = "Name", content = placeModel.name)
                        DetailsItem(title = "Address", content = placeModel.location.address ?: "")
                        DetailsItem(title = "Categories", content = placeModel.categories[0].name)
                        DetailsItem(title = "Region", content = placeModel.location.region)
                        DetailsItem(title = "Distance", content = "${placeModel.distance} miles")
                    }
                }
            } else Text(text = errorMsg, Modifier.align(Alignment.Center), fontSize = 24.sp)
        }
    }
}

@Composable
fun DetailsItem(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun ImageView(modifier: Modifier = Modifier, imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.place_img),
        error = painterResource(id = R.drawable.place_img),
        contentDescription = "Place Icon",
        modifier = modifier
    )
}
