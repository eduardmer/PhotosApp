package com.photosapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.photosapp.data.local.AppDatabase
import com.photosapp.data.local.dao.ImageDao
import com.photosapp.data.local.entities.ImageEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ImageDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var imageDao: ImageDao
    private val image = ImageEntity(1, "Eduard", 1000, 1000, "url", "download_url")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        imageDao = db.imageDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInsertDeleteRead() = runTest {
        imageDao.insertAll(listOf(image))
        val result = imageDao.getImageById(image.id)
        assertEquals(image.id, result?.id)
        assertEquals(image.author, result?.author)
        imageDao.clearAll()
        assertEquals(null, imageDao.getImageById(image.id))
    }

}