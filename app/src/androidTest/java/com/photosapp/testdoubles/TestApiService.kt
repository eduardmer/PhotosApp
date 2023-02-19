package com.photosapp.testdoubles

import com.photosapp.data.remote.ApiService
import com.photosapp.data.remote.model.Image

class TestApiService : ApiService {

    private val image1 = Image(1, "Eduard", 1000, 1000, "url", "download_url")
    private val image2 = Image(2, "Merkaj", 1001, 1001, "url", "download_url")

    override suspend fun getImages(page: Int, limit: Int): List<Image> {
        return listOf(image1, image2)
    }

}