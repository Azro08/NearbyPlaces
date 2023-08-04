package com.example.nearbyplaces.presentation.logic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.data.remote.model.PlaceResponse
import com.example.nearbyplaces.data.remote.repository.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel
@Inject constructor(private val repository: PlacesRepository) : ViewModel() {

    val responsePlaces: MutableStateFlow<ScreenState<PlaceResponse?>> =
        MutableStateFlow(ScreenState.Loading())

    fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) = viewModelScope.launch {
        responsePlaces.value = ScreenState.Loading()
        repository.getNearbyPlaces(
            coordinates = coordinates,
            radius = radius,
            limit = limit,
            sessionToken = sessionToken
        )
            .let { response ->
                try {
                    if (response.isSuccessful) {
                        Log.d("places", response.body().toString())
                        responsePlaces.value = ScreenState.Success(response.body())
                    } else {
                        responsePlaces.value = ScreenState.Error(response.message())
                    }
                } catch (ex: UnknownHostException) {
                    responsePlaces.value = ScreenState.Error(ex.message.toString())
                }
            }

    }

}
