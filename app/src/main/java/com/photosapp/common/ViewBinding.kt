package com.photosapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun ImageView.setImage(id: Int) {
    load("$IMAGE_BASE_URL/$id/300/300")
}

@BindingAdapter("image_id", "image_effect")
fun ImageView.setImageWithEffect(id: Int, effect: String) {
    load("$IMAGE_BASE_URL/$id/400/400?$effect")
}