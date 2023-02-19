package com.photosapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.photosapp.R
import com.photosapp.databinding.FragmentImageDetailsBinding
import com.photosapp.ui.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    private val args: ImageDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.image
        binding.apply {
            item = data
            viewPager.adapter = ViewPagerAdapter(requireContext(), data.id)
            TabLayoutMediator(tabLayout, viewPager){ tab, position ->
                tab.text = resources.getStringArray(R.array.images_type)[position]
                viewPager.currentItem = position
            }.attach()
            tabLayout.getTabAt(0)?.select()
        }
    }

}