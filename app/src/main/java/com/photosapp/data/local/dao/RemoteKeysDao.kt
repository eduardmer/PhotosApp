package com.photosapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photosapp.data.local.entities.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM RemoteKeys WHERE id = :id")
    suspend fun remoteKeysById(id: Int): RemoteKeys?

    @Query("DELETE FROM RemoteKeys")
    suspend fun clearAll()

}