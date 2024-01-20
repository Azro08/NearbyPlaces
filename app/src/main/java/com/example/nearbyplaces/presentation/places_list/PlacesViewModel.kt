package com.example.nearbyplaces.presentation.places_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.Place
import com.example.domain.domain.usecase.PlacesUseCase
import com.example.nearbyplaces.helper.ScreenState
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
            if (response.isNullOrEmpty()) responsePlaces.value =
                ScreenState.Error("No data available")
            else responsePlaces.value = ScreenState.Success(response)
        }

    }

}
