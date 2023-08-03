package com.example.nearbyplaces.presentation.ui.places_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nearbyplaces.data.remote.model.Results
import com.example.nearbyplaces.databinding.PlaceViewHolderBinding

class PlacesRecyclerViewAdapter(
    private val venusList: List<Results>,
    private val listener: (place: Results) -> Unit
) : RecyclerView.Adapter<PlacesRecyclerViewAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(
        listener: (place: Results) -> Unit,
        private var binding: PlaceViewHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var venue: Results? = null
        private val context = itemView.context!!
        fun bind(myVenue: Results) {
            binding.textViewPlaceName.text = myVenue.name
            binding.textViewPlaceType.text = myVenue.categories[0].name
            val address = "${myVenue.location.region}, ${myVenue.location.address}"
            binding.textViewPlaceAddress.text = address
            val iconUrl = myVenue.categories[0].icon.prefix + myVenue.categories[0].icon.suffix
            Log.d("image_url", iconUrl)
            Glide.with(context).load(iconUrl)
                .into(binding.imageViewPlaceIcon)
            venue = myVenue
        }

        init {
            binding.venueViewHolder.setOnClickListener { listener(venue!!) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            listener,
            PlaceViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return venusList.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(venusList[position])
    }

}