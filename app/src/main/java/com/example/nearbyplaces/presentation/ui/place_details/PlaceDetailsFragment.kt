package com.example.nearbyplaces.presentation.ui.place_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.R
import com.example.nearbyplaces.databinding.FragmentPlaceDetailsBinding
import com.example.nearbyplaces.domain.model.Place
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.presentation.logic.PlaceDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceDetailsFragment : Fragment() {
    private var _binding: FragmentPlaceDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlaceDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val placeId = arguments?.getString(Constants.PLACE_KEY)
        viewModelOutputs(placeId!!)
    }

    private fun viewModelOutputs(placeId: String) = with(viewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
                getPlaceDetails(placeId)
                responsePlaces.collect {
                    processResponse(it)
                }
            }
        }
    }

    private fun processResponse(state: ScreenState<Place>?) = with(binding){
        when (state) {
            is ScreenState.Loading -> {}

            is ScreenState.Success -> {
                gitLoadingDetails.visibility = View.GONE
                scrollView.visibility = View.VISIBLE

                if (state.data != null) displayDetails(state.data)
                else {
                    val errorText = "Error!"
                    textViewDetailsError.text = errorText
                    textViewDetailsError.visibility = View.VISIBLE
                }
            }

            is ScreenState.Error -> {
                gitLoadingDetails.visibility = View.GONE
                textViewDetailsError.text = state.message
                textViewDetailsError.visibility = View.VISIBLE
            }

            else -> {}
        }
    }

    private fun displayDetails(place: Place) = with(binding) {
        textViewPlaceAddress.text = place.location.address
        textViewPlaceCategory.text = place.categories[0].name
        textViewPlaceName.text = place.name
        val distance = place.distance.toString() + " m"
        textViewPlaceDistance.text = distance
        textViewPlaceRegion.text = place.location.region
        Glide.with(requireContext()).load(place.categories[0].icon)
            .error(R.drawable.place_img)
            .into(binding.imageViewPlaceImage)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}