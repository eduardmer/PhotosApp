package com.photosapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photosapp.data.local.entities.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<ImageEntity>)

    @Query("SELECT * FROM image")
    fun getImages(): PagingSource<Int, ImageEntity>

    @Query("DELETE FROM image")
    suspend fun clearAll()

}