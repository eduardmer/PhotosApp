package com.photosapp.data.remote.model

import com.photosapp.data.local.entities.ImageEntity

data class Image(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
) {
    fun toImageEntity(): ImageEntity {
        return ImageEntity(id, author, width, height, url, download_url)
    }
}
