package com.photosapp.ui.view_holder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.photosapp.databinding.ViewLoadStateBinding

class LoadStateViewHolder(private val binding: ViewLoadStateBinding) : ViewHolder(binding.root) {

    fun bind(retry: () -> Unit,  state: LoadState) {
        binding.apply {
            progressBar.isVisible = state is LoadState.Loading
            retryButton.isVisible = state is LoadState.Error
            errorText.isVisible = !(state as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorText.text = (state as? LoadState.Error)?.error?.message
            retryButton.setOnClickListener {
                retry()
            }
            binding.executePendingBindings()
        }
    }

}