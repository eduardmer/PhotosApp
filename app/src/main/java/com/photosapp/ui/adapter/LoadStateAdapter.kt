package com.photosapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.photosapp.databinding.ViewLoadStateBinding
import com.photosapp.ui.view_holder.LoadStateViewHolder

class LoadStateAdapter(private val context: Context, private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ViewLoadStateBinding.inflate(LayoutInflater.from(context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(retry, loadState)
    }

}