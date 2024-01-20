package com.example.nearbyplaces.presentation.place_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.Place
import com.example.nearbyplaces.helper.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailsViewModel @Inject constructor(private val dbRepository: com.example.data.local.repository.PlacesDbRepositoryImpl) :
    ViewModel() {
    val responsePlaces: MutableStateFlow<ScreenState<Place>?> =
        MutableStateFlow(ScreenState.Loading())

    fun getPlaceDetails(placeId: String) = viewModelScope.launch {
        responsePlaces.value = ScreenState.Loading()
        try {
            dbRepository.getPlaceByFsqId(placeId).let { place ->
                if (place != null) responsePlaces.value = ScreenState.Success(place)
                else responsePlaces.value = ScreenState.Error("Details not found!")
            }
        } catch (e: Exception) {
            responsePlaces.value = ScreenState.Error(e.message.toString())
        }
    }
}