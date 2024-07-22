package com.rago.datastore.data.repositories

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getName(): Flow<String>
    suspend fun setName(name: String)
}