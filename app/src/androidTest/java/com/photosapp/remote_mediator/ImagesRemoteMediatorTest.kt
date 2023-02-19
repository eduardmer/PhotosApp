package com.photosapp.remote_mediator

import android.content.Context
import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.photosapp.data.ImagesRemoteMediator
import com.photosapp.data.RepositoryImpl.Companion.PAGE_SIZE
import com.photosapp.data.local.AppDatabase
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.testdoubles.TestApiService
import com.photosapp.testdoubles.TestImageDao
import com.photosapp.testdoubles.TestRemoteKeysDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ImagesRemoteMediatorTest {

    private lateinit var remoteMediator: ImagesRemoteMediator

    @Before
    fun setup() {
        val service = TestApiService()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        val imageDao = TestImageDao()
        val remoteKeysDao = TestRemoteKeysDao()
        remoteMediator = ImagesRemoteMediator(service, appDatabase, imageDao, remoteKeysDao)
    }

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenNoMoreDataIsPresent() = runTest {
        val pagingState = PagingState<Int, ImageEntity>(
            listOf(),
            null,
            PagingConfig(PAGE_SIZE),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue (result is RemoteMediator.MediatorResult.Success)
        assertTrue ((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

}