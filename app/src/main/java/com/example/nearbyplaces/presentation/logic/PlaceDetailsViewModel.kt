package com.example.nearbyplaces.presentation.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.data.local.repository.PlacesDbRepository
import com.example.nearbyplaces.domain.mapper.toPlace
import com.example.nearbyplaces.domain.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailsViewModel @Inject constructor(private val dbRepository: PlacesDbRepository) : ViewModel(){
    val responsePlaces: MutableStateFlow<ScreenState<Place>?> =
        MutableStateFlow(ScreenState.Loading())

    fun getPlaceDetails(placeId : String) = viewModelScope.launch {
        responsePlaces.value = ScreenState.Loading()
        try {
            dbRepository.getPlaceByFsqId(placeId).let {
                if (it != null) responsePlaces.value = ScreenState.Success(it.toPlace())
                else responsePlaces.value = ScreenState.Error("Details not found!")
            }
        } catch (e : Exception){
            responsePlaces.value = ScreenState.Error(e.message.toString())
        }
    }
}