package com.photosapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.photosapp.data.local.AppDatabase.Companion.DB_VERSION
import com.photosapp.data.local.dao.ImageDao
import com.photosapp.data.local.dao.RemoteKeysDao
import com.photosapp.data.local.entities.ImageEntity
import com.photosapp.data.local.entities.RemoteKeys

@Database(entities = [RemoteKeys::class, ImageEntity::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "med_database"
        const val DB_VERSION = 1
    }

    abstract fun remoteKeysDao() : RemoteKeysDao

    abstract fun imageDao() : ImageDao

}