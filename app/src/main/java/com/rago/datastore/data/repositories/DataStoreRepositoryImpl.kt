package com.rago.datastore.data.repositories

import androidx.datastore.core.DataStore
import com.rago.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val myDataStore: DataStore<UserPreferences>
) : DataStoreRepository {
    override fun getName(): Flow<String> {
        return myDataStore.data.map {
            it.name
        }
    }

    override suspend fun setName(name: String) {
        myDataStore.updateData {
            it.toBuilder().setName(name).build()
        }
    }
}