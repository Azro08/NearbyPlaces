package com.example.nearbyplaces.ui.place_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.repository.PlacesDbRepositoryImpl
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.helper.ScreenState
import com.example.nearbyplaces.helper.toPlaceModel
import com.example.nearbyplaces.model.PlaceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailsViewModel @Inject constructor(
    private val dbRepository: PlacesDbRepositoryImpl,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _responsePlaces: MutableStateFlow<ScreenState<PlaceModel>?> =
        MutableStateFlow(ScreenState.Loading())
    val responsePlaces get() = _responsePlaces

    init {
        savedStateHandle.get<String>(Constants.PLACE_KEY)?.let { placeId ->
            getPlaceDetails(placeId)
        }
    }

    private fun getPlaceDetails(placeId: String) = viewModelScope.launch {
        _responsePlaces.value = ScreenState.Loading()
        try {
            dbRepository.getPlaceByFsqId(placeId).let { place ->
                if (place != null) _responsePlaces.value = ScreenState.Success(place.toPlaceModel())
                else _responsePlaces.value = ScreenState.Error("Details not found!")
            }
        } catch (e: Exception) {
            _responsePlaces.value = ScreenState.Error(e.message.toString())
        }
    }
}