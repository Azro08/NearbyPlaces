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
    private val _responsePlace: MutableStateFlow<ScreenState<PlaceModel>?> =
        MutableStateFlow(ScreenState.Loading())
    val responsePlace get() = _responsePlace

    init {
        savedStateHandle.get<String>(Constants.PLACE_KEY)?.let { placeId ->
            getPlaceDetails(placeId)
        }
    }

    private fun getPlaceDetails(placeId: String) = viewModelScope.launch {
        _responsePlace.value = ScreenState.Loading()
        try {
            dbRepository.getPlaceByFsqId(placeId).let { place ->
                if (place != null) _responsePlace.value = ScreenState.Success(place.toPlaceModel())
                else _responsePlace.value = ScreenState.Error("Details not found!")
            }
        } catch (e: Exception) {
            _responsePlace.value = ScreenState.Error(e.message.toString())
        }
    }
}