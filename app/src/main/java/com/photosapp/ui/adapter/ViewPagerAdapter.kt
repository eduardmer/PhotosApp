package com.photosapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.photosapp.R
import com.photosapp.databinding.ViewImageBinding

class ViewPagerAdapter(private val context: Context, private val imageId: Int) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ViewImageBinding) : ViewHolder(binding.root) {
        fun bind(id: Int, effect: String) {
            binding.imageId = id
            binding.imageEffect = effect
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewImageBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(imageId, context.resources.getStringArray(R.array.images_type)[position].lowercase())
    }

    override fun getItemCount(): Int {
        return 3
    }

}