package com.photosapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.photosapp.data.local.AppDatabase
import com.photosapp.data.local.dao.ImageDao
import com.photosapp.data.local.dao.RemoteKeysDao
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.data.local.entities.RemoteKeys
import com.photosapp.data.remote.ApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ImagesRemoteMediator @Inject constructor(
    private val service: ApiService,
    private val appDatabase: AppDatabase,
    private val imageDao: ImageDao,
    private val remoteKeysDao: RemoteKeysDao) : RemoteMediator<Int, ImageEntity>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ImageEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        return try {
            val response = service.getImages(page, if (page == 1) state.config.initialLoadSize else state.config.pageSize)
            val endOfPaginationReached = response.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearAll()
                    imageDao.clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + response.size / RepositoryImpl.PAGE_SIZE
                val keys = response.map {
                    RemoteKeys(it.id, prevKey, nextKey)
                }
                remoteKeysDao.insertAll(keys)
                val items = response.map {
                    it.toImageEntity()
                }
                imageDao.insertAll(items)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            exception.printStackTrace()
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ImageEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                remoteKeysDao.remoteKeysById(it.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ImageEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                remoteKeysDao.remoteKeysById(it.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ImageEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                remoteKeysDao.remoteKeysById(it)
            }
        }
    }

}