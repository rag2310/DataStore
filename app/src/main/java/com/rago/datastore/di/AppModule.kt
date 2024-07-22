package com.rago.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.rago.datastore.UserPreferences
import com.rago.datastore.data.repositories.DataStoreRepository
import com.rago.datastore.data.repositories.DataStoreRepositoryImpl
import com.rago.datastore.data.serializer.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(MyDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = UserPreferencesSerializer(),
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            migrations = listOf()
        ) {
            context.dataStoreFile("user_preferences.pb")
        }

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        dataStore: DataStore<UserPreferences>
    ): DataStoreRepository = DataStoreRepositoryImpl(dataStore)
}