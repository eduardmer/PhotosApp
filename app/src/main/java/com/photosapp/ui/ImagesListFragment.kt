package com.photosapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.databinding.FragmentImagesListBinding
import com.photosapp.ui.adapter.ImageAdapter
import com.photosapp.ui.adapter.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesListFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentImagesListBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImageAdapter(requireContext(), this)
        val footerAdapter = LoadStateAdapter(requireContext()) { adapter.retry() }
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter.withLoadStateFooter(footerAdapter)
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    when(it.mediator?.refresh) {
                        is LoadState.Loading -> showProgressBar(true)
                        is LoadState.Error -> {
                            showProgressBar(false)
                            footerAdapter.loadState = it.mediator?.refresh as LoadState.Error
                        }
                        else -> showProgressBar(false)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun setOnItemClickListener(item: ImageEntity) {
        findNavController().navigate(ImagesListFragmentDirections.actionImagesListFragmentToImageDetailsFragment(item.author, item))
    }

}