package com.example.freeimagesearching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freeimagesearching.data.models.PhotoResponse
import com.example.freeimagesearching.databinding.ItemPhotoBinding

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var photos: List<PhotoResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: PhotoResponse) {
            Glide.with(binding.root)
                .load(photos.urls?.regular)
                .into(binding.photoImageView)

            Glide.with(binding.root)
                .load(photos.user?.profileImageUrls?.small)
                .into(binding.profileImageView)

            if (photos.user?.name.isNullOrBlank()) {
                binding.authorTextView.visibility = View.GONE
            } else {
                binding.authorTextView.visibility = View.VISIBLE
                binding.authorTextView.text = photos.user?.name
            }
        }
    }
}