package com.example.nearbyplaces.presentation.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.domain.usecase.Place
import com.example.nearbyplaces.domain.usecase.PlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel
@Inject constructor(private val useCase: PlacesUseCase) : ViewModel() {

    val responsePlaces: MutableStateFlow<ScreenState<List<Place>?>> =
        MutableStateFlow(ScreenState.Loading())

    fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) = viewModelScope.launch {
        responsePlaces.value = ScreenState.Loading()

        useCase.invoke(
            coordinates = coordinates,
            radius = radius,
            limit = limit,
            sessionToken = sessionToken
        ).let { response ->
            responsePlaces.value = response
        }

    }

}
