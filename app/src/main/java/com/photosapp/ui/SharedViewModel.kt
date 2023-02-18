package com.photosapp.ui

import androidx.lifecycle.ViewModel
import com.photosapp.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val items = repository.getImages()

}