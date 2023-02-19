package com.photosapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.photosapp.data.local.dao.ImageDao
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val remoteMediator: ImagesRemoteMediator,
    private val imageDao: ImageDao) : Repository {

    companion object {
        const val PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE = 60
        const val PREFETCH_DISTANCE = 15
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getImages(): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false,
                initialLoadSize = INITIAL_LOAD_SIZE),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                imageDao.getImages()
            }
        ).flow
    }

}