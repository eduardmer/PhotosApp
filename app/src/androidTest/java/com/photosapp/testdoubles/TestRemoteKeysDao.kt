package com.photosapp.testdoubles

import com.photosapp.data.local.dao.RemoteKeysDao
import com.photosapp.data.local.entities.RemoteKeys

class TestRemoteKeysDao : RemoteKeysDao {

    private val remoteKeys = mutableListOf(
        RemoteKeys(0, 1, 3)
    )

    override suspend fun insertAll(remoteKey: List<RemoteKeys>) {
        remoteKeys.addAll(remoteKey)
    }

    override suspend fun remoteKeysById(id: Int): RemoteKeys? {
        return remoteKeys.find { it.id == id }
    }

    override suspend fun clearAll() {
        remoteKeys.clear()
    }

}