package com.photosapp

import com.photosapp.data.remote.model.Image
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageTest {

    @Test
    fun imageToImageEntity() {
        val image = Image(1, "Eduard", 1000, 1000, "url", "download_url")
        val imageEntity = image.toImageEntity()
        assertEquals(image.id, imageEntity.id)
        assertEquals(image.author, imageEntity.author)
        assertEquals(image.width, imageEntity.width)
        assertEquals(image.height, imageEntity.height)
        assertEquals(image.url, imageEntity.url)
        assertEquals(image.download_url, imageEntity.download_url)
    }

}