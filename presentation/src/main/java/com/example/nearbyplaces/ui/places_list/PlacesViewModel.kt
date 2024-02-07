package com.example.nearbyplaces.ui.places_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.PlacesUseCase
import com.example.nearbyplaces.helper.ScreenState
import com.example.nearbyplaces.helper.toPlaceModel
import com.example.nearbyplaces.model.PlaceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel
@Inject constructor(private val useCase: PlacesUseCase) : ViewModel() {

    private val _responsePlaces: MutableStateFlow<ScreenState<List<PlaceModel>?>> =
        MutableStateFlow(ScreenState.Loading())
    val responsePlaces get() = _responsePlaces

    fun getNearbyPlaces(
        coordinates: String,
        radius: Int,
        limit: Int,
        sessionToken: String,
    ) = viewModelScope.launch {
        _responsePlaces.value = ScreenState.Loading()

        useCase.invoke(
            coordinates = coordinates,
            radius = radius,
            limit = limit,
            sessionToken = sessionToken
        ).let { response ->
            if (response.isNullOrEmpty()) _responsePlaces.value =
                ScreenState.Error("No data available")
            else _responsePlaces.value = ScreenState.Success(response.map { it.toPlaceModel() })
        }

    }

}
