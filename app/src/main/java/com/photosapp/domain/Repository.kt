package com.photosapp.domain

import androidx.paging.PagingData
import com.photosapp.data.local.entities.ImageEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getImages(): Flow<PagingData<ImageEntity>>

}