package com.example.nearbyplaces.presentation.ui.places_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foursquareapplication.helper.ScreenState
import com.example.nearbyplaces.R
import com.example.nearbyplaces.databinding.FragmentPlacesBinding
import com.example.nearbyplaces.domain.model.Place
import com.example.nearbyplaces.helper.AuthManager
import com.example.nearbyplaces.helper.Constants
import com.example.nearbyplaces.presentation.logic.PlacesViewModel
import com.example.nearbyplaces.presentation.ui.splash_screen.SplashScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlacesFragment : Fragment() {
    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!
    private var recyclerViewAdapter: PlacesRecyclerViewAdapter? = null
    private val viewModel: PlacesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlacesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setMenu()
        viewModelOutputs()
    }

    private fun viewModelOutputs() = with(viewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val accessToken = AuthManager.getAuthenticationToken(requireContext())
                Log.d("access_token", accessToken)
                val ll = "53.9057644,27.558230"
                val limit = 30
                val radius = 4000
                getNearbyPlaces(
                    coordinates = ll,
                    limit = limit,
                    radius = radius,
                    sessionToken = accessToken
                )
                responsePlaces.collect {
                    processResponse(it)
                }
            }
        }
    }

    private fun processResponse(state: ScreenState<List<Place>?>) = with(binding) {
        when (state) {

            is ScreenState.Loading -> {}

            is ScreenState.Success -> {
                gitLoading.visibility = View.GONE
                recyclerViewNearby.visibility = View.VISIBLE
                if (state.data != null) displayPlaces(state.data)
            }

            is ScreenState.Error -> {
                gitLoading.visibility = View.GONE
                recyclerViewNearby.visibility = View.VISIBLE
                textViewError.text = state.message
                textViewError.visibility = View.VISIBLE
            }

        }
    }

    private fun displayPlaces(places: List<Place>) = with(binding) {
        Log.d("results", places.size.toString())
        recyclerViewAdapter = PlacesRecyclerViewAdapter(places) {
            findNavController().navigate(R.id.nav_to_details, bundleOf(Pair(Constants.PLACE_KEY, it.fsqId)))
        }
        recyclerViewNearby.setHasFixedSize(true)
        recyclerViewNearby.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewNearby.adapter = recyclerViewAdapter

        swipeRefreshLayout.setOnRefreshListener {
            refreshFragment()
        }
    }

    private fun refreshFragment() {
        binding.swipeRefreshLayout.isRefreshing = false
        viewModelOutputs()
    }



    private fun setMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.itemLogout -> {
                        AuthManager.clearAuthenticationToken(requireContext())
                        val intent = Intent(requireContext(), SplashScreenActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        recyclerViewAdapter = null
    }

}