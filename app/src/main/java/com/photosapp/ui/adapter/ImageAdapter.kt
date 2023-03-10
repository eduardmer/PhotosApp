package com.photosapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.databinding.ViewRecyclerItemBinding
import com.photosapp.ui.view_holder.ImageViewHolder

class ImageAdapter(private val context: Context, private val listener: OnItemClickListener) : PagingDataAdapter<ImageEntity, ImageViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ViewRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ImageViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class DiffCallBack : DiffUtil.ItemCallback<ImageEntity>() {
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun setOnItemClickListener(item: ImageEntity)
    }

}