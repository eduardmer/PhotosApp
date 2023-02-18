package com.photosapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.photosapp.data.remote.model.Image

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)