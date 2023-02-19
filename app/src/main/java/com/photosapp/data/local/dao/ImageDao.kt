package com.photosapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photosapp.data.local.entities.ImageEntity
import org.jetbrains.annotations.TestOnly

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageEntity>)

    @Query("SELECT * FROM image")
    fun getImages(): PagingSource<Int, ImageEntity>

    @TestOnly
    @Query("SELECT * FROM image WHERE id = :id")
    suspend fun getImageById(id: Int): ImageEntity?

    @Query("DELETE FROM image")
    suspend fun clearAll()

}