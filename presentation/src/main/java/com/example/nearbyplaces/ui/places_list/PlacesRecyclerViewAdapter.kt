package com.example.nearbyplaces.ui.places_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nearbyplaces.R
import com.example.nearbyplaces.databinding.PlaceViewHolderBinding
import com.example.nearbyplaces.model.PlaceModel

class PlacesRecyclerViewAdapter(
    private val venusList: List<PlaceModel>,
    private val listener: (place: PlaceModel) -> Unit
) : RecyclerView.Adapter<PlacesRecyclerViewAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(
        listener: (place: PlaceModel) -> Unit,
        private var binding: PlaceViewHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var venue: PlaceModel? = null
        fun bind(myVenue: PlaceModel) {
            binding.textViewPlaceName.text = myVenue.name
            binding.textViewPlaceType.text = myVenue.categories[0].name
            val address = "${myVenue.location.region}, ${myVenue.location.address}"
            binding.textViewPlaceAddress.text = address
            val iconUrl = myVenue.categories[0].icon.prefix + myVenue.categories[0].icon.suffix
            Log.d("image_url", iconUrl)
            Glide.with(itemView.context!!).load(iconUrl)
                .error(R.drawable.place_img)
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