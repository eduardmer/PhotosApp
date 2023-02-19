package com.photosapp.testdoubles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.photosapp.data.local.entities.ImageEntity

class TestPagingSource : PagingSource<Int, ImageEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ImageEntity>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageEntity> {
        return LoadResult.Page(
            listOf(ImageEntity(1, "Eduard", 1000, 1000, "url", "download_url")),
            null,
            2
        )
    }
}