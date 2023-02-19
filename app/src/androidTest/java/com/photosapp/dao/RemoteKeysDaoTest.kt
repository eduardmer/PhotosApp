package com.photosapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.photosapp.data.local.AppDatabase
import com.photosapp.data.local.dao.RemoteKeysDao
import com.photosapp.data.local.entities.RemoteKeys
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteKeysDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var remoteKeysDao: RemoteKeysDao
    private val item = RemoteKeys(0, 1, 3)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        remoteKeysDao = db.remoteKeysDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInsertDeleteRead() = runTest {
        remoteKeysDao.clearAll()
        Assert.assertEquals(null, remoteKeysDao.remoteKeysById(0))
        remoteKeysDao.insertAll(listOf(item))
        Assert.assertEquals(0, remoteKeysDao.remoteKeysById(0)?.id)
        remoteKeysDao.clearAll()
        Assert.assertEquals(null, remoteKeysDao.remoteKeysById(0))
    }

}