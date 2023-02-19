package com.photosapp.testdoubles

import androidx.paging.PagingSource
import com.photosapp.data.local.dao.ImageDao
import com.photosapp.data.local.entities.ImageEntity

class TestImageDao : ImageDao {

    private val images = mutableListOf(
        ImageEntity(1, "Eduard", 1000, 1000, "url", "download_url")
    )

    override suspend fun insertAll(images: List<ImageEntity>) {
        this.images.addAll(images)
    }

    override fun getImages(): PagingSource<Int, ImageEntity> {
        return TestPagingSource()
    }

    override suspend fun getImageById(id: Int): ImageEntity? {
        return images.find { it.id == id }
    }

    override suspend fun clearAll() {
        images.clear()
    }

}