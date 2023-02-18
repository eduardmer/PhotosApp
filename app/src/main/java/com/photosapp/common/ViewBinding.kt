package com.photosapp.common

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImage(imageUrl: String) {
    this.load(imageUrl)
}

@BindingAdapter("isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}